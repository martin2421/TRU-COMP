from gpiozero import Buzzer
import RPi.GPIO as GPIO
import time
from Adafruit_IO import Client, Feed, RequestError

ADAFRUIT_IO_KEY = 'aio_nmdJ118BtUXQkBh81WJMHfsD0BRm'
ADAFRUIT_IO_USERNAME = 'trvadnais'

aio = Client(ADAFRUIT_IO_USERNAME, ADAFRUIT_IO_KEY)

try: # if we have a 'digital' feed
    digital = aio.feeds('distance')
except RequestError: # create a digital feed
    feed = Feed(name="distance")
    digital = aio.create_feed(feed)

# GPIO Mode (BOARD / BCM)
GPIO.setmode(GPIO.BCM)
 
# Set GPIO Pins
GPIO_TRIGGER = 16
GPIO_ECHO = 12

buzzer = Buzzer(6)
 
# Set GPIO direction (IN / OUT)
GPIO.setup(GPIO_TRIGGER, GPIO.OUT)
GPIO.setup(GPIO_ECHO, GPIO.IN)
 
def distance():
    #Set Trigger to HIGH
    GPIO.output(GPIO_TRIGGER, True)
    
    #Set Trigger after 0.01ms to LOW
    time.sleep(0.00001)
    GPIO.output(GPIO_TRIGGER, False)

    StartTime = time.time()
    StopTime = time.time()
 
    #Save StartTime
    while GPIO.input(GPIO_ECHO) == 0:
        StartTime = time.time()

    #Save time of arrival
    while GPIO.input(GPIO_ECHO) == 1:
        StopTime = time.time()
 
    #Time difference between start and arrival
    TimeElapsed = StopTime - StartTime
    
    #Multiply with the sonic speed (34300 cm/s) and divide by 2, because there and back
    distance = (TimeElapsed * 34300) / 2
    return distance
 
if __name__ == '__main__':
   try:
       while True:
           dist = distance()
           print("Measured Distance = %.1f cm" % dist)
           time.sleep(1)
           dist_value = 0
           data = aio.receive(digital.key)
           
           if(dist < 10):  
               dist_value = dist
               if(float(data.value) < 10):
                   buzzer.beep(0.05, 0.1)
                   
    
           elif(dist>10 and dist <=25):
               dist_value = dist
               if(float(data.value) > 10 and float(data.value) <= 25):
                   buzzer.beep(0.05, 0.6)
               
               
           elif(dist>25 and dist<=75):
               dist_value = dist
               if(float(data.value) > 25 and float(data.value) <= 75):
                   buzzer.beep(0.5, 0.5)
               
           else:
               buzzer.off()
           
           aio.send(digital.key, dist_value)
           
           
 
    #Reset by pressing CTRL + C
   except KeyboardInterrupt:
       print("Measurement stopped by User")
       GPIO.cleanup()
