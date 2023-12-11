import RPi.GPIO as GPIO
import Adafruit_DHT
import time

# Set the GPIO mode and pin for the LED
LED_PIN = 18  # You can change this to the desired GPIO pin
GPIO.setmode(GPIO.BCM)
GPIO.setup(LED_PIN, GPIO.OUT)

# Initialize the DHT sensor (change DHT22 to DHT11 if using that sensor)
DHT_SENSOR = Adafruit_DHT.DHT22
DHT_PIN = 4  # GPIO pin where the DHT sensor is connected

# Set up PWM for the LED
pwm = GPIO.PWM(LED_PIN, 100)  # PWM frequency: 100 Hz
pwm.start(0)  # Start PWM with 0% duty cycle

try:
    while True:
        # Read temperature and humidity from the DHT sensor
        humidity, temperature = Adafruit_DHT.read(DHT_SENSOR, DHT_PIN)

        if humidity is not None and temperature is not None:
            print(f"Temperature: {temperature:.1f}°C, Humidity: {humidity:.1f}%")

            # Adjust LED brightness based on temperature
            if temperature < 20:
                pwm.ChangeDutyCycle(20)  # 20% brightness
            elif 20 <= temperature <= 25:
                pwm.ChangeDutyCycle(50)  # 50% brightness
            else:
                pwm.ChangeDutyCycle(100)  # 100% brightness

        time.sleep(5)  # Delay for 5 seconds between readings

except KeyboardInterrupt:
    pass

finally:
    # Clean up GPIO and PWM when the script is stopped
    pwm.stop()
    GPIO.cleanup()