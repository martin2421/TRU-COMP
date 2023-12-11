from gpiozero import Buzzer
import time
import RPi.GPIO as GPIO
import pyrebase

config ={
	"apiKey": "AlzaSyDKco5ay2xPggs8uYZ5PeBzd4rVLVEqzRM",
	"authDomain":"test-1-711d6.firebaseapp.com",
	"databaseURL":"https://test-1-711d6-default-rtdb.firebaseio.com",
	"storageBucket":"test-1-711db6.appspot.com"
}
firebase = pyrebase.initialize_app(config)
print(firebase)
db = firebase.database()
print(db)

firebase_availability_path = "Availability"
flag = False


GPIO_TRIGGER = 17
GPIO_ECHO = 4

buzzer = Buzzer(27)

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
    data = 1
    while True:
		
        dist = distance()
        print("Measured Distance = %.1f cm" % dist)
        time.sleep(1)
        

        if(dist < 100):
            
            template = {
                "Availability": str(data)

            }
            if(not flag):
                data = 0 
                db.child(firebase_availability_path).set(str(data))
                flag = True
                print("data sent")


        else:
             if(flag):
                flag = False
                x = {"Availability" : data}
                data = 1
                db.child(firebase_availability_path).set(str(data)) 
