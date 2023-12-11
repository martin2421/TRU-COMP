 // function to identify an even number
 function isEven(num) {
     return num % 2 === 0;
}

// function to calculate the factorial of a number
function factorial(num) {
     if (num === 0 || num === 1) return 1;
     else return num * factorial(num - 1);
}

// function to create a student object
function createStudent(name, age, major) {
     let student = {
          name: name,
          age: age,
          major: major,

          getAge: () => { return this.age; },
          setAge: (newAge) => { this.age = newAge; }
     };
     return student;
}

// function to read multiple numbers and returns them in an array
function readNumbers(n) {
     let numbersArray = [];

     for (let i = 0; i < n; i++) {
          let input = prompt("Enter number " + (i+1) + ":");
          let number = parseFloat(input);

          if (!isNaN(number)) numbersArray.push(number);
          else {
               console.log(`Invalid entry!`);
               i--;
          }
     }
     return numbersArray;
}

// function that computes the average of an array of numbers
function average(numbers) {
     if (numbers.length === 0) return null;

     let sum = 0;
     for (let i = 0; i < numbers.length; i++) {
          sum += numbers[i];
     }

     return sum / numbers.length;
}

// Testing isEven function
let a = prompt("Enter a number to find if it is even: ");
alert("Is number even?: " + isEven(a));

// Testing factorial function
let b = prompt("Enter a number to find it's factorial: ");
alert("Factorial of " + b + ": " + factorial(b));

// Testing creation of student object
let sname = prompt("Enter name of student: ");
let age = prompt("Enter age of student: ");
let major = prompt("Enter major of student: ");
let student1 = createStudent(sname, age, major);
student1.setAge(25);
alert("Student created!\n\nName: " + student1.name + "\nAge: " + 
student1.getAge() + "\nMajor: " + student1.major);

// Testing readNumbers function
let count = prompt("Enter how many numbers to input in the array: ");
let numberArray = readNumbers(count);
alert("Array created!\n" + numberArray.toString());

// Testing average function
let avg = average(numberArray);
alert("Average of array: " + avg);