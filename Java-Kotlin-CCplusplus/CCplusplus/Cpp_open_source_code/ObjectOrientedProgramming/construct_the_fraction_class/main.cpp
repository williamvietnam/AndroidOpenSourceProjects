
#include <bits/stdc++.h>
using namespace std;
 
class Phanso
{
private:
    int tuso, mauso;
 
public:
    Phanso()
    {
        tuso = 0;
        mauso = 1;
    }
 
    ~Phanso()
    {
        tuso = 0;
        mauso = 1;
    }
//-----------------------------------------------------------------//
    void set()
    {
        cout << "Nhap Tu So: "; cin >> this->tuso;
        cout << "Nhap Mau So: "; cin >> this-> mauso;
    }
 
    void get()
    {
        cout << this->tuso << "/" << this->mauso << endl;
    }
//-----------------------------------------------------------------//
    Phanso Cong(Phanso obj1, Phanso obj2)
    {
        Phanso obj3;
        obj3.tuso = obj1.tuso * obj2.mauso + obj1.mauso * obj2.tuso;
        obj3.mauso = obj1.mauso * obj2.mauso;
        return obj3;
    }
    Phanso Tru(Phanso obj1, Phanso obj2)
    {
        Phanso obj3;
        obj3.tuso = obj1.tuso * obj2.mauso - obj1.mauso * obj2.tuso;
        obj3.mauso = obj1.mauso * obj2.mauso;
        return obj3;
    }
    Phanso Nhan(Phanso obj1, Phanso obj2)
    {
        Phanso obj3;
        obj3.tuso = obj1.tuso * obj2.tuso;
        obj3.mauso = obj1.mauso * obj2.mauso;
        return obj3;
    }
    Phanso Chia(Phanso obj1, Phanso obj2)
    {
        Phanso obj3;
        obj3.tuso = obj1.tuso * obj2.mauso;
        obj3.mauso = obj1.mauso * obj2.tuso;
        return obj3;
    }
};
 
int main()
{
    Phanso obj1, obj2, obj3;
    obj1.set(); obj2.set();
 
    obj3 = obj3.Cong(obj1, obj2); obj3.get();
    obj3 = obj3.Tru(obj1, obj2); obj3.get();
    obj3 = obj3.Nhan(obj1, obj2); obj3.get();
    obj3 = obj3.Chia(obj1, obj2); obj3.get();
 
    return 0;
}