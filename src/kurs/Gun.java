package kurs;

import java.util.Random;

public class Gun {
    private final int BULLETS_IN_MAGAZINE = 15;
    private int bullets = 0;
    private int magazines = 10;
    private Random random = new Random();
    private boolean locked = false;

    public boolean shot() {
        if (locked) {
            System.out.printf("  была осечка, патрон застрял, смените магазин, их еще %d\n", magazines);
            return true;
        }

        if (bullets == 0) {
            if (!reload()) {
                return false;
            }
        }

        int randomInt = random.nextInt(10);
        //System.out.printf(" %d\n", randomInt);
        boolean misfire = (randomInt == 1);
        if (misfire) {
            locked = true;
            System.out.printf("  осечка, патронов осталось %d\n", bullets);
            return true;
        }

        bullets--;
        System.out.printf("  выстрел, патронов осталось %d\n", bullets);
        return true;
    }

    public boolean shot(int bullets) {
        for (int i = 0; i < bullets; i++) {
            if (!shot()) {
                return false;
            }
        }
        return true;
    }

    public boolean reload() {
        if (magazines == 0) {
            System.out.println("  больше нет магазинов");
            return false;
        }
        locked = false;
        magazines--;
        bullets = BULLETS_IN_MAGAZINE;
        System.out.printf("  вставлен новый магазин, осталось %d\n", magazines);
        return true;
    }

    @Override
    public String toString() {
        return String.format("пистолет \"миротворец\":  патронов %d, магазинов %d", bullets, magazines);
    }

    public String amount() {
        String template =
                "{\n" +
                "    \"locked\":%b,\n" +
                "    \"magazines\":%d,\n" +
                "    \"bullets\":%d\n" +
                "}";
        return String.format(template, locked, magazines, bullets);
    }
}

