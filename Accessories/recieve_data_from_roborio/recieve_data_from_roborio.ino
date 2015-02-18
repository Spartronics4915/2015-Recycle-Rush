#include <Wire.h>
#include "FastLED.h"

#define NUM_LEDS 80

#define LEDS_PER_INCH .5 // find actual value for LEDS_PER_INCH

#define DATA_PIN 11
#define CLOCK_PIN 13

#define BLUE CRGB(0, 50, 170)

CRGB leds[NUM_LEDS];


/**
* Test found on chief delphi
* credit to Rakusan2 of team 3571
*/

int height = 0;

void setup()
{
  FastLED.addLeds<WS2801, DATA_PIN, CLOCK_PIN, RGB>(leds, NUM_LEDS);
  FastLED.setBrightness(30);
  pinMode (CLOCK_PIN, OUTPUT);
  Wire.begin(4);                // join i2c bus with address #4
  Wire.onReceive(receiveEvent); // register event
}

void loop()
{
  delay(100);
  displayLedsAtHeightOfElevator();
}

void displayLedsAtHeightOfElevator()
{
  int numLedsToDisplay = height * LEDS_PER_INCH;
  
  for (int i = 0; i < numLedsToDisplay; i++) {
    leds[i] = BLUE;
  }
  
  FastLED.show();
}

// function that executes whenever data is received from master
// this function is registered as an event, see setup()
void receiveEvent(int howMany)
{
  String Height = "";

  while ( Wire.available() > 0 )
  {
    char n = (char)Wire.read();
    if (((int)n) > ((int)(' ')))
      Height += n;
  }

  height = atoi(Height.c_str());
  
}
