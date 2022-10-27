package step3;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static step3.Lotto.*;

public class Lottos {

    private static final List<Integer> CANDIDATE_NUMBERS;

    private final List<Lotto> lottos;

    static {
        CANDIDATE_NUMBERS = IntStream
                .rangeClosed(TICKET_MIN_VALUE, TICKET_MAX_VALUE)
                .boxed().collect(Collectors.toList());
    }

    public Lottos(List<Lotto> lottos){

        this.lottos = lottos;
    }

    public void addByAuto() {
        Collections.shuffle(CANDIDATE_NUMBERS);
        Lotto lotto = new Lotto(CANDIDATE_NUMBERS
                .stream()
                .limit(NUMBER_SIZE)
                .collect(Collectors.toList()));
        lottos.add(lotto);
    }

    public int getSumOfPriceLottos(){
        return lottos.stream()
                .mapToInt(Lotto::getPrice)
                .sum();
    }

    public List<List<Integer>> getAllLottoNumbers() {
        return lottos.stream()
                .map(Lotto::getNumbers)
                .collect(Collectors.toList());
    }
}
