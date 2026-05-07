package training;

public class AlakzatMain {

    static void main() {
        new AlakzatMain().run();
    }

    private void run() {
        var kor = new Kor(new Pont(1, 2), 3.0);
        var kozeppont = szamitKozeppont(kor);
        IO.println(kozeppont);
    }

    private Pont szamitKozeppont(Alakzat alakzat) {
        return switch (alakzat) {
            case Kor(var kozeppont, _) -> kozeppont;
            case Teglalap(var a, var b) -> new Pont(
                    (a.x() + b.x()) / 2,
                    (a.y() + b.y()) / 2
                );
        };
    }
}
