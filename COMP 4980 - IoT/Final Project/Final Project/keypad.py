import RPi.GPIO as GPIO
import time
import pyrebase
from gpiozero import LED
import subprocess

def run_external_program(program_path):
    try:
        subprocess.Popen(['lxterminal', '-e', 'python', program_path])
    except Exception as e:
        print(f"An error occurred while running {program_path}: {e}")

config ={
    "apiKey": "AlzaSyDKco5ay2xPggs8uYZ5PeBzd4rVLVEqzRM",
    "authDomain":"test-1-711d6.firebaseapp.com",
    "databaseURL":"https://test-1-711d6-default-rtdb.firebaseio.com",
    "storageBucket":"test-1-711db6.appspot.com"
}

firebase = pyrebase.initialize_app(config)
db = firebase.database()

led_granted = LED(24)
led_denied = LED(23)

firebase_path_password = "Password"

first_input_received = False  # Flag for the first input

def stream_handler(message):
    global first_input_received
    if not first_input_received:
        print("Welcome to the admin program manager. Please enter the correct code to run the programs.")

        return

    print("Checking the entered code...")
    
    if message["data"] == "True":
        print("Access Granted. Activating granted LED...")
        led_granted.on()
        time.sleep(3)
        led_granted.off()
        print("Running external programs...")
        run_external_program('automatedNetworkTraffic.py')
        run_external_program('buzzer.py')
        run_external_program('proximity.py')
        run_external_program('temperature.py')
    else:
        print("Access Denied. Activating denied LED...")
        print("Please try again...")
        led_denied.on()
        led_granted.off()
        time.sleep(3)
        led_denied.off()

    first_input_received = False  # Reset the flag after checking the password

my_stream = db.child("Allowed").stream(stream_handler)

L1, L2, L3, L4 = 5, 6, 13, 19
C1, C2, C3, C4 = 12, 16, 20, 21

keypadPressed = -1
input = ""

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
GPIO.setup([L1, L2, L3, L4], GPIO.OUT)
GPIO.setup([C1, C2, C3, C4], GPIO.IN, pull_up_down=GPIO.PUD_DOWN)

def keypadCallback(channel):
    global keypadPressed
    if keypadPressed == -1:
        keypadPressed = channel

GPIO.add_event_detect(C1, GPIO.RISING, callback=keypadCallback)
GPIO.add_event_detect(C2, GPIO.RISING, callback=keypadCallback)
GPIO.add_event_detect(C3, GPIO.RISING, callback=keypadCallback)
GPIO.add_event_detect(C4, GPIO.RISING, callback=keypadCallback)

def setAllLines(state):
    GPIO.output([L1, L2, L3, L4], state)

def checkSpecialKeys():
    global input, first_input_received
    pressed = False
    
    GPIO.output(L3, GPIO.HIGH)
    if GPIO.input(C4) == 1:
        print("Input Reset!")
        input = ""
        pressed = True
    GPIO.output(L3, GPIO.LOW)

    GPIO.output(L1, GPIO.HIGH)
    if not pressed and GPIO.input(C4) == 1:
        db.child(firebase_path_password).set(input)
        pressed = True
        print("Password Sent")
    GPIO.output(L1, GPIO.LOW)

    if pressed:
        input = ""
        first_input_received = False  # Reset the flag

    return pressed

def readLine(line, characters):
    global input
    GPIO.output(line, GPIO.HIGH)
    for i, char in enumerate(characters):
        if GPIO.input([C1, C2, C3, C4][i]) == 1:
            print(char)
            input += char
    GPIO.output(line, GPIO.LOW)

try:
    while True:
        if keypadPressed != -1:
            first_input_received = True  # Set the flag when first input is detected
            setAllLines(GPIO.HIGH)
            if GPIO.input(keypadPressed) == 0:
                keypadPressed = -1
            time.sleep(0.1)
        else:
            if not checkSpecialKeys():
                readLine(L1, ["1", "2", "3", "A"])
                readLine(L2, ["4", "5", "6", "B"])
                readLine(L3, ["7", "8", "9", "C"])
                readLine(L4, ["*", "0", "#", "D"])
            time.sleep(0.1)
except KeyboardInterrupt:
    print("\nApplication Stopped.")
    GPIO.cleanup()  # Clean up GPIO on CTRL+C exit
