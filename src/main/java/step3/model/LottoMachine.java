package step3.model;

import step3.model.dto.LottoResultDto;
import step3.model.dto.LottosNumberDto;
import step3.model.dto.RankDto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoMachine {

    private final int purchasePrice;
    private final Lottos lottos;
    private final LottoMoney lottoMoney;
    private static final int LOTTO_PRICE = 1000;

    public LottoMachine(int purchasePrice) {
        this.lottoMoney = new LottoMoney(purchasePrice);
        this.purchasePrice = purchasePrice;
        int size = purchasePrice / LOTTO_PRICE;
        this.lottos = new Lottos(LottoFactory.createLottos(size), LOTTO_PRICE);
    }

    public LottoResultDto getLottoResult(List<LottoNumber> winningNumbers) {
        Map<Rank, Integer> rankOfLottos = lottos.getRankOfLottos(winningNumbers);
        List<RankDto> rankDtos = getRanks(rankOfLottos);
        double getPriceRatio = getPriceRatio(rankOfLottos, purchasePrice);
        return new LottoResultDto(rankDtos, getPriceRatio);
    }

    public LottosNumberDto getLottoNumber() {
        return new LottosNumberDto(lottos);
    }

    private List<RankDto> getRanks(Map<Rank, Integer> rankOfLottos) {
        return Arrays.stream(Rank.values())
                .map(rank -> new RankDto(rank, rankOfLottos.getOrDefault(rank, 0)))
                .collect(Collectors.toList());
    }

    private double getPriceRatio(Map<Rank, Integer> rankOfLottos, int purchasePrice) {
        int sumOfRankPrice = Arrays.stream(Rank.values())
                .mapToInt(rank -> rank.getWinningPrice() * rankOfLottos.getOrDefault(rank, 0))
                .sum();
        return sumOfRankPrice / (double) purchasePrice;
    }
}
