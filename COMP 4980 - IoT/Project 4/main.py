from gpiozero import LED, Button, Buzzer
from time import sleep, time
import random

# initialize GPIO components
led = LED(23)
left_button = Button(24)
right_button = Button(22)
buzzer = Buzzer(25)

# player names
left_name = input("Left player name is: ")
right_name = input("Right player name is: ")

# win variables
wins_left = 0
wins_right = 0

# function to play a round
def play_round(level):
     led.off()
     buzzer.off()
     
     countdown = random.randint(1, 6) # random countdown between 1-5 sec
     
     sleep(1)
     led.off()

     start_time = time()
     while (time() - start_time < countdown):
          remaining_time = int(countdown - (time() - start_time))
          print(f"Countdown: {remaining_time} seconds", end="\r")
          if left_button.is_pressed or right_button.is_pressed:
               buzzer.off()
               led.off()
               print("Fail! Button pressed too soon.")
               return None
     
     buzzer.on()
     led.on()
     sleep(0.5)
     
     while True:
          if left_button.is_pressed:
               print(f"Win! {left_name} pressed after the buzzer. ")
               return "left"
          elif right_button.is_pressed:
               print(f"Win! {right_name} pressed after the buzzer. ")
               return "right"
          
# Main game loop
for level in range(1, 6): # 5 games will be played
     print(f"Level {level}: {left_name} vs {right_name}")
     result = play_round(level)
     
     if result == "left":
          wins_left += 1
     elif result == "right":
          wins_right += 1
          
print("\nGame Over!")
print(f"Scores - {left_name}: {wins_left}, {right_name}: {wins_right}")

if wins_left > wins_right:
     print(f"{left_name} wins!")
elif wins_right > wins_left:
     print(f"{right_name} wins!")
else:
     print("It's a tie!")
     
# Clean GPIO
led.close()
left_button.close()
right_button.close()    
buzzer.close()
