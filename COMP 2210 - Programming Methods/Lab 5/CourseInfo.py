# dictionary with course number and room number
course_room = {'CS101': 3004, 'CS102': 4501,
               'CS103': 6755, 'NT110': 1244,
               'CM241': 1411}

# dictionary with course number and instructor
course_instructor = {'CS101': "Haynes", 'CS102': "Alvarado",
                     'CS103': "Rich", 'NT110': "Burke",
                     'CM241': "Lee"}

# dictionary with course number and meeting time
course_schedule = {'CS101': "8:00am", 'CS102': "9:00am",
                   'CS103': "10:00am", 'NT110': "11:00am",
                   'CM241': "1:00pm"}

# asks user for course number
course = ""
course = input("Enter course number for details: ")

# validation
while (course != "CS101" and course != "CS102" and course != "CS103" and course != "NT110" and course != "CM241"):
     print("Invalid course. Try again..")
     course = input("Enter course number for details: ")
  
# output   
print(course)
print("Room #:", course_room.get(course))
print("Instructor #:", course_instructor.get(course))
print("Meeting Time #:", course_room.get(course))

