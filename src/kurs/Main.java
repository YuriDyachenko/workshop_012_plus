package kurs;
/*
Сегодня пишем класс-пистолет.
У пистолета есть магазин. В магазине может быть максимум 15 патронов. При этом у нас изначально 10 магазинов.
Методы:
shot() - производит выстрел. При этом в текущем магазине становится на один патрон меньше. Если в текущем
магазине нет патронов, магазин автоматически меняется на новый, только после этого происходит выстрел.
reload() - перезаряжаем магазин на новый. При этом если в старом магазине остались патроны, они теряются.
Если нового магазина нет, бросается исключение OutOfMagazines. Старый магазин при этом не меняется,
т.е. если в нем были патроны, они остаются.
amount() - возвращается JSON, например: {"magazines": 5, "bullets": 3} - это значит, что у нас есть еще
5 новых магазинов и три патрона в текущем.

При выстреле может произойти осечка с 10% вероятностью. Пистолет попадает в состояние locked.
Патрон при этом не тратится. Но все последующие вызовы shot() должны бросать исключение
LockedException и не производить выстрела.
Чтобы вывести пистолет из состояния locked, необходимо вызвать метод reload(), который заменит
магазин на новый.

Пистолет может перегреться. Нормальная температура пистолета - 20 градусов. Ниже нее пистолет опуститься
не может. Каждый выстрел повышает температуру на 1 градус. При этом каждую секунду пистолет остывает на
один градус, но не ниже нормальной (20 градусов). То есть если мы сделали три выстрела подряд, температура
пистолета станет 23 градуса. Если мы потом подождем 1 секунду, температура опустится до 22 градусов.
Если температура пистолета становится > 60 градусов, при попытке выстрелить происходит исключение
HeatException, выстрел не происходит. После того как температура падает до 60 градусов и ниже,
выстрелы снова становятся доступными.
*/
public class Main {

    public static void main(String[] args) {
        Input input = new Input();
        Gun gun = new Gun();
        System.out.println(gun);

        while (true) {
            printMenu();
            int answer = input.enterInt("Введите номер операции", 0, 4);
            if (answer == 0) break;
            if (answer == 1) gun.shot();
            if (answer == 2) {
                int shots = input.enterInt("Сколько раз выстрелить", 2, 10);
                gun.shot(shots);
            }
            if (answer == 3) gun.reload();
            if (answer == 4) System.out.println(gun.amount());
        }

        input.close();
    }

    public static void printMenu() {
        System.out.println("---------------------------------");
        System.out.println("1. Один выстрел");
        System.out.println("2. Несколько выстрелов");
        System.out.println("3. Сменить магазин");
        System.out.println("4. Состояние в JSON");
        System.out.println("0. Выход");
        System.out.println("---------------------------------");
    }
}