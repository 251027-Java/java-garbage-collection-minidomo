import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

class read {
    static void main(String[] args) throws IOException {
        var pat = Pattern.compile(".*Pause.* ([\\d.]+)ms$");
        var lines = Files.readAllLines(Path.of(args[0]));

        var pauses = lines.stream()
                .map(String::trim)
                .filter(e -> pat.matcher(e).matches())
                .toList();

        var pauseTimes = pauses.stream()
                .map(e -> {
                    var m = pat.matcher(e);
                    m.find();
                    return Double.parseDouble(m.group(1));
                })
                .toList();

        System.out.println(pauses.stream().filter(e -> e.contains("Young")).count() + " young");
        System.out.println(pauses.stream().filter(e -> e.contains("Full")).count() + " full");

        System.out.println(pauseTimes.size() + " pauses");
        System.out.println(pauseTimes.stream().max(Double::compare).get() + " ms max");

        var last = lines.getLast();
        var a = Pattern.compile(".*([\\d.]+).*").matcher(last);
        a.find();
        var runtime = Double.parseDouble(a.group(1)) * 1000;

        var sum = pauseTimes.stream().mapToDouble(Double::doubleValue).sum();
        System.out.println(sum + " ms gc total");
        System.out.printf("%.3f\n", runtime / (runtime + sum) * 100);
    }
}
