#include <Wire.h>

/**
* Test found on chief delphi
* credit to Rakusan2 of team 3571
*/

void setup()
{
  pinMode (13, OUTPUT);
  Wire.begin(4);                // join i2c bus with address #4
  Wire.onReceive(receiveEvent); // register event
}

void loop()
{
  delay(100);
}

// function that executes whenever data is received from master
// this function is registered as an event, see setup()
void receiveEvent(int howMany)
{
  String LED = "";

  while ( Wire.available() > 0 )
  {
    char n = (char)Wire.read();
    if (((int)n) > ((int)(' ')))
      LED += n;
  }

  if (LED == "go")
  {

    digitalWrite (13, HIGH);


  }
}
