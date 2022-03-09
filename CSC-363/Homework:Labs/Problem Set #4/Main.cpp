#include <iostream>
#include <string>

#include "AbstractTester.hpp"
#include "LinkedListTester.hpp"

void run(const std::string& test, AbstractTester& tester)
{
    std::cout << test << std::endl;
    tester.runAll();
    tester.report();
}

// // Visual Studio Leak Detection (Begin)
// #define _CRTDBG_MAP_ALLOC      
// #include <stdlib.h>
// #include <crtdbg.h>
// // Visual Studio Leak Detection (End)

int main()
{
    //_CrtSetDbgFlag(_CRTDBG_ALLOC_MEM_DF | _CRTDBG_LEAK_CHECK_DF);

	LinkedListTester tester;
    run("Linked List", tester);

    system("pause");

    return 0;
}