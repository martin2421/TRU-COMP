import os
import pyshark
import json
from datetime import datetime
from collections import Counter
from urllib.parse import urlparse
import time
import threading
import pyrebase



# Function to extract the main domain from a URL
def extract_main_domain(url):
    parsed_url = urlparse(url)
    domain = parsed_url.netloc if parsed_url.netloc else parsed_url.path
    parts = domain.split('.')
    if len(parts) > 2:
        return '.'.join(parts[-2:])
    else:
        return domain
    
def upload_to_firebase():
    config ={
        "apiKey": "AlzaSyDKco5ay2xPggs8uYZ5PeBzd4rVLVEqzRM",
        "authDomain":"test-1-711d6.firebaseapp.com",
        "databaseURL":"https://test-1-711d6-default-rtdb.firebaseio.com/",
        "storageBucket":"test-1-711db6.appspot.com"
    }
    firebase = pyrebase.initialize_app(config)
    print(firebase)
    db = firebase.database()
    print(db)
    data = {}
    firebase_path_network = "NetworkTraffic"
    with open("network_traffic.json", 'r') as json_file:

        data = json.load(json_file)



    template = {
                    "NetworkTraffic": data
                    }

    db.child(firebase_path_network).set(template["NetworkTraffic"])



    print('Data uploaded successfully!')

def extract_network_traffic_info(pcap_file, output_file):
    # Open the pcap file using Pyshark
    cap = pyshark.FileCapture(pcap_file)

    # Create a list to hold the extracted data
    data = []

    # Create counters for protocols, source IP addresses, and destination IP addresses
    protocol_counter = Counter()
    src_ip_counter = Counter()
    dest_ip_counter = Counter()
    dns_counter = Counter()

    # List of common domains to filter out
    common_domains = ['internal.example.com']

    # Initialize variables for total number of packets, total data transfer, and timestamps
    total_packets = 0
    total_data_transfer = 0
    start_timestamp = None
    end_timestamp = None
    latencies = []
    jitter = 0
    last_latency = None
    last_timestamp = None

    # Initialize a dictionary to hold the DNS resolutions
    dns_resolutions = {}

    # Loop through each packet in the capture
    for pkt in cap:
        # Convert the timestamp to a human-readable format
        timestamp = float(pkt.sniff_timestamp)
        if start_timestamp is None or timestamp < start_timestamp:
            start_timestamp = timestamp
        if end_timestamp is None or timestamp > end_timestamp:
            end_timestamp = timestamp

        timestamp_str = datetime.utcfromtimestamp(timestamp).strftime('%Y-%m-%d %H:%M:%S')

        # Extract the source and destination IP addresses
        src_ip = pkt.ip.src if 'IP' in pkt else 'N/A'
        dest_ip = pkt.ip.dst if 'IP' in pkt else 'N/A'

        # Extract the protocol
        protocol = pkt.highest_layer

        # Extract the length of the packet
        length = int(pkt.length)

        # Extract additional information
        info = pkt.info if 'INFO' in pkt else 'N/A'

        # If DNS packet, extract main domain
        if 'DNS' in pkt and hasattr(pkt.dns, 'qry_name'):
            dns_query_name = pkt.dns.qry_name
            main_domain = extract_main_domain(dns_query_name)
            if main_domain not in common_domains:  # Filter out common domains
                dns_counter[main_domain] += 1
                # Store the DNS resolution in the dictionary
                if hasattr(pkt.dns, 'a'):
                    dns_resolutions[pkt.dns.a] = main_domain

        # Calculate latency and jitter
        if last_timestamp is not None:
            latency = timestamp - last_timestamp
            latencies.append(latency)
            current_jitter = abs(latency - last_latency) if last_latency is not None else 0
            jitter += current_jitter
        else:
            latency = 0
            current_jitter = 0

        # Update last_latency and last_timestamp for next iteration
        last_latency = latency if 'latency' in locals() else None
        last_timestamp = timestamp

        # Create a dictionary with the extracted information
        packet_info = {
            'timestamp': timestamp_str,
            'src_ip': src_ip,
            'dest_ip': dest_ip,
            'protocol': protocol,
            'length': length,
            'info': info,
            'latency': f"{latency * 1e3:.3f} ms",
            'jitter': f"{current_jitter * 1e3:.3f} ms",
        }

        # Add the DNS URL to the packet info, if available
        dns_url = dns_resolutions.get(dest_ip) or dns_resolutions.get(src_ip)
        if dns_url:
            packet_info['dns_url'] = dns_url

        # Add the dictionary to the list
        data.append(packet_info)

        # Update counters and totals
        protocol_counter[protocol] += 1
        src_ip_counter[src_ip] += 1
        dest_ip_counter[dest_ip] += 1
        total_packets += 1
        total_data_transfer += length

    # Calculate average latency and jitter
    average_latency = sum(latencies) / len(latencies) if latencies else 0
    average_jitter = jitter / (len(latencies) - 1) if len(latencies) > 1 else 0

    # Calculate bandwidth (in Mbps)
    duration = end_timestamp - start_timestamp  # in seconds
    bandwidth = (total_data_transfer * 8) / (duration * 1e6)  # in Mbps
    bandwidth = round(bandwidth, 3)  # round to 3 decimal places

    # Convert the list of dictionaries to JSON format
    json_data = json.dumps({
        'summary': {
            'total_packets': total_packets,
            'total_data_transfer': total_data_transfer,
            'bandwidth': f"{bandwidth} Mbps",
            'top_protocols': protocol_counter.most_common(5),
            'top_src_ips': src_ip_counter.most_common(5),
            'top_dest_ips': dest_ip_counter.most_common(5),
            'average_latency': f"{average_latency * 1e3:.3f} ms",
            'average_jitter': f"{average_jitter * 1e3:.3f} ms",
            'top_dns': dns_counter.most_common(30),
        }
    }, indent=4)

    # Write the JSON data to the output file
    with open(output_file, 'w') as f:
        f.write(json_data)
    
    # Comment indicating the extraction has finished
    print(f"Finished extracting data to {output_file}")
        
    cap.close()  # Close the capture file to free resources

# Function to capture network traffic
def capture_packets(interface='en0', duration=30, output_file='network_traffic.pcap'):
    print("===== CAPTURING PACKETS =====")
    capture = pyshark.LiveCapture(interface=interface, output_file=output_file)

    try:
        capture.sniff(timeout=duration)
        print("===== PACKET CAPTURE COMPLETED =====")
    except Exception as e:
        print(f"An error occurred during packet capture: {e}")
    finally:
        capture.close()
        print(f"Packets saved to {output_file}")
        # After capture, process the packets immediately
        json_output_file = output_file.replace('.pcap', '.json')
        extract_network_traffic_info(output_file, json_output_file)

# Function to stop capturing based on user input
def wait_for_enter():
    input("Press Enter to stop capturing at the next opportunity...\n")
    global capture_flag
    capture_flag = False




# The main loop to start capturing and processing
def main(interface='wlan0', capture_duration=30):
    global capture_flag
    capture_flag = True
    thread = threading.Thread(target=wait_for_enter)
    thread.start()

    # Define the filenames for pcap and JSON, which will be overwritten each time
    pcap_filename = 'network_traffic.pcap'
    json_filename = 'network_traffic.json'

    while capture_flag:

        capture_packets(interface, capture_duration, pcap_filename)
        upload_to_firebase()
        

        # Sleep or perform other tasks here if necessary
        time.sleep(1)  # Short sleep to allow for thread context switching

    thread.join()
    print("===== PROGRAM STOPPED BY USER =====")

if __name__ == "__main__":
    main()
