#ifndef DIVIDEBYZEROEXCEPTION_H_INCLUDED
#define DIVIDEBYZEROEXCEPTION_H_INCLUDED

#include <stdexcept> // stdexcept header contains runtime_error

class DivideByZeroException : public std::runtime_error
{
public:
    // constructor specifies default error message
    DivideByZeroException()
        : std::runtime_error{"cannot divide by zero!"} {}
};

#endif // DIVIDEBYZEROEXCEPTION_H_INCLUDED
