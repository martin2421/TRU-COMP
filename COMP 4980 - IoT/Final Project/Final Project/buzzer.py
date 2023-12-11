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

buzzer = Buzzer(27)


def stream_handler(message):
	print("Checking for intruder")
	if(message["data"] == "True"):
		buzzer.on()
		print("INTRUDER DETECTED")
	else:
		buzzer.off()
		print("INTRUDER NEUTRALIZE, SYSTEM SAFE")
        
	
my_stream = db.child("Intruder").stream(stream_handler)


