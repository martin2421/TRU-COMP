import RPi.GPIO as GPIO
import Adafruit_DHT
import time
import pyrebase


DHT_SENSOR = Adafruit_DHT.DHT11
DHT_PIN = 25

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
firebase_path_temp = "temperature"
firebase_path_humid = "humidity"


temp_data=0.0
humid_data=0.0



try:
	while True:
		# Read the temperature and humidity from the DHT sensor
		humidity,temperature = Adafruit_DHT.read(DHT_SENSOR,DHT_PIN)
		
		if humidity is not None and temperature is not None:
			print(f"Temperature: {temperature:.1f}C,Humidity : {humidity:.1f}%")
			
			template_temp = {
                    "temperature": temperature
                    
                    }
			template_humid = {
                    "humidity": humidity
                    
                    }

			db.child(firebase_path_temp).set(temperature)
			db.child(firebase_path_humid).set(humidity)
			
			
		time.sleep(2)#delay for 5 seconds between readings
		
		
except KeyboardInterrupt:
	pass
	
finally:
	#clean up GPI and PWM when the script is topped
	pwm.stop()
	GPIO.cleanup()
