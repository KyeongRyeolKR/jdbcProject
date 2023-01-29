import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pokemonCount = sc.nextInt();
        int problemCount = sc.nextInt();
        sc.nextLine();
        HashMap<Integer, String> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        for (int i = 0; i < pokemonCount; i++) {
            String pokemon = sc.nextLine();
            map1.put(i + 1, pokemon);
            map2.put(pokemon, i + 1);
        }

        List<String> problems = new ArrayList<>();
        for (int i = 0; i < problemCount; i++) {
            problems.add(sc.nextLine());
            if (Character.isDigit(problems.get(i).charAt(0))) {
                System.out.println(map1.get(Integer.parseInt(problems.get(i))));
            } else {
                System.out.println(map2.get(problems.get(i)));
            }
        }
    }
}
