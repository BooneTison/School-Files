/*
* @author (Student Name)
* <p> (File Name)
* <p> (Assignment)
* <p> (Describe, in general, the code contained.)
*/

#ifndef _LINKED_LIST_TESTER_H
#define _LINKED_LIST_TESTER_H 1

#include <map>
#include <iostream>

#include "AbstractTester.hpp"
#include "LinkedList.hpp"

class LinkedListTester : public AbstractTester
{
public:
    LinkedListTester(const std::string& name = "Linked List", std::ostream& out = std::cout) : AbstractTester( name, out )
    {
    }

    virtual void runAll()
    {
        //
        // Run tests and report
        //
        _errorCountMap["constructor"] = testConstructor();
        _errorCountMap["first"] = testFirst();
        _errorCountMap["last"] = testLast();
        _errorCountMap["push"] = testPush();
        _errorCountMap["pop"] = testPop();
        _errorCountMap["operator[]"] = testOperator();
        _errorCountMap["contains"] = testContains();
        _errorCountMap["remove"] = testRemove();
        _errorCountMap["search"] = testSearch();
        _errorCountMap["clear"] = testClear();
    }

    virtual int testConstructor()
    {
        _os << "\t" << "Test LinkedList<T>()...";
        int errors = 0;
        LinkedList<int> list;

        if (list.size() != 0)
        {
            emitError("Constructor error; size not zero");
            errors++;
        }
        LinkedListTester::check(list, errors);

        if (!list.isEmpty())
        {
            emitError("Constructor error; linked list not empty");
            errors++;
        }
        LinkedListTester::check(list, errors);

        if (list.search(_distribution(_generator)) != -1)
        {
            emitError("Constructor error; search returned something other than -1");
            errors++;
        }
        LinkedListTester::check(list, errors);

        int popped = list.pop();
        if (popped != int{})
        {
            emitError("Constructor error; pop did not return default T{} for empty linked list");
            errors++;
        }
        LinkedListTester::check(list, errors);

        _os << "done" << std::endl;

        return errors;
    }

    virtual int testFirst()
    {
        _os << "\t" << "Test first()...";
        int errors = 0;
        LinkedList<int> list;

        int returned = list.first();
        if (returned != int{})
        {
            emitError("Error on first() default", returned, int{});
            errors++;
        }
        LinkedListTester::check(list, errors);

        for (int value = 0; value < 10; value++)
        {
            list.push(value);

            int returned = list.first();
            if (returned != value)
            {
                emitError("Error on last()", returned, value);
                errors++;
            }
            LinkedListTester::check(list, errors);
        }

        // One-element lists
        for (int value = 0; value < 10; value++)
        {
            list.push(value);

            int returned = list.first();
            if (returned != value)
            {
                emitError("Error on last()", returned, value);
                errors++;
            }
            LinkedListTester::check(list, errors);

            list.clear();
        }

        _os << "done" << std::endl;

        return errors;
    }

    virtual int testLast()
    {
        _os << "\t" << "Test last()...";
        int errors = 0;
        LinkedList<int> list;

        int returned = list.last();
        if (returned != int{})
        {
            emitError("Error on last() default", returned, int{});
            errors++;
        }
        LinkedListTester::check(list, errors);

        int LAST = 0;
        for (int value = 0; value < 10; value++)
        {
            list.push(value);

            int returned = list.last();
            if (returned != LAST)
            {
                emitError("Error on last()", returned, LAST);
                errors++;
            }
            LinkedListTester::check(list, errors);
        }

        // One-element lists
        for (int value = 0; value < 10; value++)
        {
            list.push(value);

            int returned = list.last();
            if (returned != value)
            {
                emitError("Error on first() with clear", returned, value);
                errors++;
            }
            LinkedListTester::check(list, errors);

            list.clear();
        }

        _os << "done" << std::endl;

        return errors;
    }

    virtual int testPush()
    {
        _os << "\t" << "Test push()...";
        int errors = 0;
        LinkedList<int> list;

        int LAST = 0;
        for (int i = 0; i < 10; i++)
        {
            if (list.size() != i)
            {
                emitError("Push error (before): size", list.size(), i);
                errors++;
            }

            ///////////////////
            list.push(i);
            ///////////////////
            LinkedListTester::check(list, errors);

            int first = list.first();
            if (first != i)
            {
                emitError("Push error (first)", first, i);
                errors++;
            }
            int last = list.last();
            if (last != LAST)
            {
                emitError("Push error (last)", last, LAST);
                errors++;
            }
            if (list.isEmpty())
            {
                emitError("Push error: empty; linked list empty");
                errors++;
            }
            if (list.size() != i + 1)
            {
                emitError("Push error (after): size", list.size(), i + 1);
                errors++;
            }
            LinkedListTester::check(list, errors);
        }

        _os << "done" << std::endl;

        return errors;
    }

    virtual int testPop()
    {
        _os << "\t" << "Test pop()...";
        int errors = 0;
        LinkedList<int> list;

        if (list.pop())
        {
            emitError("Pop error; successful pop() on default empty linked list");
            errors++;
        }
        LinkedListTester::check(list, errors);

        //
        // Add a bunch of stuff
        //
        for (int i = 1; i <= 10; i++)
        {
            list.push(i);
        }
        LinkedListTester::check(list, errors);

        // Remove all of it
        for (int i = 10; i >= 1; i--)
        {
            if (!list.pop())
            {
                emitError("Pop() failed; should have suceeded");
                errors++;
            }
            if (list.size() != i - 1)
            {
                emitError("Pop error: size", list.size(), i - 1);
                errors++;
            }
            LinkedListTester::check(list, errors);
        }

        if (list.size() != 0)
        {
            emitError("Pop error; size not zero");
            errors++;
        }
        LinkedListTester::check(list, errors);

        if (!list.isEmpty())
        {
            emitError("Pop error; linked list not empty");
            errors++;
        }
        LinkedListTester::check(list, errors);

        if (list.search(_distribution(_generator)) != -1)
        {
            emitError("Pop error; search returned something other than -1");
            errors++;
        }
        LinkedListTester::check(list, errors);

        if (list.pop())
        {
            emitError("Pop() succeeded; should have failed");
            errors++;
        }
        LinkedListTester::check(list, errors);

        _os << "done" << std::endl;

        return errors;
    }

    virtual int testOperator()
    {
        _os << "\t" << "Test operator[]()...";
        int errors = 0;
        LinkedList<int> list;

        try
        {
            list[0];
            emitError("operator[]() expected exception to be thrown; was not.");
            errors++;
        }
        catch (const std::string&) {}

        LinkedListTester::check(list, errors);

        //
        // Add a bunch of stuff
        //
        for (int i = 1; i <= 10; i++)
        {
            list.push(i);

            for (int j = 0; j < i; j++)
            {
                if (list[j] != i - j)
                {
                    emitError("operator[]()", list[j], i - j);
                    errors++;
                }
            }
            LinkedListTester::check(list, errors);
        }

        // Repeat
        for (int i = 1; i < 5; i++)
        {
            try
            {
                list[(_distribution(_generator) + 12) % 1000];
                emitError("operator[]() expected exception to be thrown; was not.");
                errors++;
            }
            catch (const std::string&) {}

            LinkedListTester::check(list, errors);
        }

        list.clear();
        try
        {
            list[_distribution(_generator) % 1000];
            emitError("operator[]() expected exception to be thrown; was not.");
            errors++;
        }
        catch (const std::string&) {}

        LinkedListTester::check(list, errors);

        _os << "done" << std::endl;

        return errors;
    }

    virtual int testContains()
    {
        _os << "\t" << "Test contains...";
        int errors = 0;
        LinkedList<int> list;

        //
        // Successful finding
        //
        for (int value = 0; value < 10; value++)
        {
            list.push(value);

            for (int i = 0; i <= value; i++)
            {
                if (!list.contains(i))
                {
                    emitError("Expected list to contain " + std::to_string(i));
                    errors++;
                }
                LinkedListTester::check(list, errors);
            }
            LinkedListTester::check(list, errors);
        }

        //
        // Unsuccessful finding
        //
        list.clear();
        for (int value = 0; value < 10; value++)
        {
            if (list.contains(value))
            {
                emitError("Expected list NOT to contain() [" + std::to_string(value) + "]");
                errors++;
            }
            LinkedListTester::check(list, errors);

            list.push(value);

            // unsuccessful find
            for (int i = 0; i <= value; i++)
            {
                int rand = _distribution(_generator) + 11 % 10000;
                if (list.contains(rand))
                {
                    emitError("Expected list NOT to contain() [" + std::to_string(rand) + "]");
                    errors++;
                }
                LinkedListTester::check(list, errors);
            }
            LinkedListTester::check(list, errors);
        }

        _os << "done" << std::endl;

        return errors;
    }

    virtual int testRemove()
    {
        _os << "\t" << "Test remove()...";
        int errors = 0;
        LinkedList<int> list;

        if (list.remove(_distribution(_generator)))
        {
            emitError("remove() returned true on empty list.");
            errors++;
        }
        LinkedListTester::check(list, errors);

        //
        // Add / Remove
        //
        for (int i = 1; i <= 10; i++)
        {
            list.push(i);

            if (!list.remove(i))
            {
                emitError("remove() add / remove" + std::to_string(i));
                errors++;
            }
            LinkedListTester::check(list, errors);
        }

        //
        // Add all (random elements) then remove.
        // Repeat this several times
        //
        for (int count = 0; count < 5; count++)
        {
            const int SIZE = _distribution(_generator) % 100 + 10;
            for (int i = 0; i < SIZE; i++)
            {
                list.push(_distribution(_generator));
            }

            for (int i = SIZE; i >= 1; i--)
            {
                int valueToRemove = list[_distribution(_generator) % i];

                if (!list.remove(valueToRemove))
                {
                    emitError("remove() random loop[" + std::to_string(count) +
                              "] value [" + std::to_string(valueToRemove) +"]");
                    errors++;
                }
                LinkedListTester::check(list, errors);
            }
        }

        _os << "done" << std::endl;

        return errors;
    }

    virtual int testSearch()
    {
        _os << "\t" << "Test search()...";
        int errors = 0;
        LinkedList<int> list;

        //
        // Successful finding
        //
        for (int value = 0; value < 10; value++)
        {
            list.push(value);

            for (int i = 0; i <= value; i++)
            {
                int searchIndex = list.search(i);
                if (searchIndex != list.size() - i)
                {
                    emitError("Successful search() error", searchIndex, list.size() - i);
                    errors++;
                }
                LinkedListTester::check(list, errors);
            }
            LinkedListTester::check(list, errors);
        }

        //
        // Unsuccessful finding
        //
        list.clear();
        for (int value = 0; value < 10; value++)
        {
            int searchIndex = list.search(value);
            if (searchIndex != -1)
            {
                emitError("Unsuccessful search() error", searchIndex, -1);
                errors++;
            }
            LinkedListTester::check(list, errors);

            list.push(value);

            // unsuccessful find
            for (int i = 0; i <= value; i++)
            {
                int searchIndex = list.search(_distribution(_generator) + 11);
                if (searchIndex != -1)
                {
                    emitError("Unsuccessful search error (2nd)", searchIndex, -1);
                    errors++;
                }
                LinkedListTester::check(list, errors);
            }
            LinkedListTester::check(list, errors);
        }

        _os << "done" << std::endl;

        return errors;
    }


    virtual int testClear()
    {
        _os << "\t" << "Test clear...";
        int errors = 0;
        LinkedList<int> list;

        list.clear();
        LinkedListTester::check(list, errors);

        if (!list.isEmpty())
        {
            emitError("Clear error: empty; linked list not empty");
            errors++;
        }
        if (list.size() != 0)
        {
            emitError("Clear error (after): size", list.size(), 0);
            errors++;
        }

        for (int i = 0; i < 10; i++)
        {
            list.push(i);
        }

        LinkedListTester::check(list, errors);
        list.clear();

        if (!list.isEmpty())
        {
            emitError("Clear error: empty; linked list not empty");
            errors++;
        }
        if (list.size() != 0)
        {
            emitError("Clear error (after): size", list.size(), 0);
            errors++;
        }

        LinkedListTester::check(list, errors);

        _os << "done" << std::endl;

        return errors;
    }

    template<typename U>
    void check(const LinkedList<U>& list, int& errors)
    {
        AbstractTester::check();

        if (!list.check())
        {
            emitError("Verification check failed");
            errors++;
        }
        return;
    }
};

#endif