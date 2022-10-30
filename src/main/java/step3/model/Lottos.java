package step3.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lottos {

    private final List<Lotto> lottos;
    private final int lottoPrice;

    public Lottos(List<Lotto> lottos, int lottoPrice) {
        this.lottos = lottos;
        this.lottoPrice = lottoPrice;
    }

    public int getSumOfPriceLottos() {
        return lottos.size() * lottoPrice;
    }

    public List<List<LottoNumber>> getNumbersOfLottos() {
        return lottos.stream()
                .map(Lotto::getNumbers)
                .collect(Collectors.toList());
    }

    public Map<Rank, Integer> getRankOfLottos(List<LottoNumber> winningNumbers) {
        HashMap<Rank, Integer> result = new HashMap<>();
        lottos.forEach(lotto -> {
            Rank rank = lotto.getRank(winningNumbers);
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        });
        return result;
    }
}
