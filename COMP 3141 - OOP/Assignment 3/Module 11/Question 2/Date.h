#ifndef DATE_H_INCLUDED
#define DATE_H_INCLUDED

// date class definition
class Date
{
public:
  Date();
  Date(int x, int y, int z);
  void getData();
  void getCurrentDate();
  void getDate();
  int getLeapYearDays();
  int getTotalDays();
  int month;
  //~Date();
private:
  int day;

  int year;
  const int monthDays[12] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
}; // end class Time

#endif // DATE_H_INCLUDED
