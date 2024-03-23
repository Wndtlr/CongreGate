#include <SoftwareSerial.h>

// Initialize variables
const int trigPin = 3;
const int echoPin = 2;
const int ButPin = 12;
long duration;
int distanceA;
int lastDistanceA = 0;
int lastDistanceB = 0;
int hit = 0;

void setup() {
  // Start the serial communication
  Serial.begin(9600);

  // Define the TRIG and ECHO as output and input pins
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
  pinMode(ButPin, INPUT);
  digitalWrite(ButPin,HIGH);
}

void loop() {
  // Clear the trigPin
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  // Set the trigPin high for 10 microseconds
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
  
  // Read the echoPin and calculate the distance
  duration = pulseIn(echoPin, HIGH);
  distanceA = duration * 0.034 / 2;

//Replaced by button for now
  //   // Clear the trigPin
  // digitalWrite(trigPin, LOW);
  // delayMicroseconds(2);
  // // Set the trigPin high for 10 microseconds
  // digitalWrite(trigPin, HIGH);
  // delayMicroseconds(10);
  // digitalWrite(trigPin, LOW);
  
  // // Read the echoPin and calculate the distance
  // duration = pulseIn(echoPin, HIGH);
  // distanceB = duration * 0.034 / 2;


  // Check if the distance has changed significantly
  if (abs(distanceA - lastDistanceA) > 2) { 
    for(int i=0;i<2000;i++){
      if(digitalRead(ButPin)==LOW){
        delay(20);
        if(digitalRead(ButPin)== LOW){
          hit=1;
          break;
        }
      }
      else{
        hit=0;
      }  
      delay(1);
    }
  } 

  // Update lastDistance
  lastDistanceA = distanceA;

    // Check if the distance has changed significantly
  if (abs(distanceA - lastDistanceA) > 2) { 
    for(int i=0;i<2000;i++){
      if(digitalRead(ButPin)==LOW){
        delay(20);
        if(digitalRead(ButPin)== LOW){
          hit=1;
          break;
        }
      }
      else{
        hit=0;
      }  
      delay(1);
    }
  } 

  // // Update lastDistance
  // lastDistanceA = distanceA;

  //    // Check if the distance has changed significantly
  // else if (abs(distanceB - lastDistanceB > 2) { 
  //   for(int i=0;i<2000;i++){
  //     if(digitalRead(ButPin)==LOW){
  //       delay(20);
  //       if(digitalRead(ButPin)== LOW){
  //         hit=-1;
  //         break;
  //       }
  //     }
  //     else{
  //       hit=0;
  //     }  
  //     delay(1);
  //   }
  // } 
  //   // Update lastDistance
  // lastDistanceB = distanceB;

  // Send the distance and hit status over serial
  Serial.print(" Hit: ");
  Serial.println(hit);

  delay(1000); // Delay between measurements
}


