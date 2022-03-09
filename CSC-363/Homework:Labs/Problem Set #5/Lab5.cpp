#include <iostream>
/*
Boone Tison
Lab5.cpp
Problem Set #5
02/08/2022
Implements invert and setbits fucntions and tests them
Experiments with shifting with negative values
*/

// 3. Returns x with the n bits that begin at position p inverted
unsigned invert(unsigned x, int p, int n) {
    unsigned mask = ~(~0 << n) << p;
    return x ^ mask;
}

// 4. Returns x with the n bits that begin at position p set to the
//    rightmost n bits of y
unsigned setbits(unsigned x, int p, int n, unsigned y) {
    unsigned cast = ~(~(~0 << n) << p);
    unsigned mold = (y & ~(~0 << n)) << p;
    return x & cast | mold;
}

int main()
{
    // 1. Bit shifting with a negative value
    unsigned i = 0x45 << -2; // This gives a random hex value of 8 bits
    unsigned k = 0x45 >> -2; // This outputs as 1
    unsigned j = 0x45 >> 2;
    std::cout << std::hex << i << " " << k << " " << j << std::endl;

    // Test invert
    std::cout << "Test Invert" << std::endl;
    std::cout << "Actual: " << std::hex << invert(0xFF,2,2) << " Expected: 0xF3" << std::endl;
    std::cout << "Actual: " << std::hex << invert(0xFB,2,2) << " Expected: 0xF7" << std::endl;
    std::cout << "Actual: " << std::hex << invert(0x00,0,2) << " Expected: 0x03" << std::endl;
    std::cout << "Actual: " << std::hex << invert(0x00,4,2) << " Expected: 0x30" << std::endl;
    std::cout << "Actual: " << std::hex << invert(0xFF,0,0) << " Expected: 0xFF" << std::endl;
    std::cout << "Actual: " << std::hex << invert(0x43,3,5) << " Expected: 0xBB" << std::endl;

    // Test setbits
    std::cout << "Test Setbits" << std::endl;
    std::cout << "Actual: " << std::hex << setbits(0x00,2,2,0xFF) << " Expected: 0x0C" << std::endl;
    std::cout << "Actual: " << std::hex << setbits(0x00,0,2,0xFF) << " Expected: 0x03" << std::endl;
    std::cout << "Actual: " << std::hex << setbits(0x00,4,4,0xFF) << " Expected: 0xF0" << std::endl;
    std::cout << "Actual: " << std::hex << setbits(0x00,0,8,0xFF) << " Expected: 0xFF" << std::endl;
    std::cout << "Actual: " << std::hex << setbits(0x00,0,0,0xFF) << " Expected: 0x00" << std::endl;
    std::cout << "Actual: " << std::hex << setbits(0x03,0,4,0x0C) << " Expected: 0x0C" << std::endl;
    std::cout << "Actual: " << std::hex << setbits(0x03,1,3,0x0C) << " Expected: 0x09" << std::endl;

    return 0;
}