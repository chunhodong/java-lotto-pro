package step3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import step3.model.*;
import step3.model.dto.LottoResultDto;
import step3.model.dto.LottosNumberDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachineTest {

    List<LottoNumber> getLottoNumbers(int... numbers) {

        List<LottoNumber> lottoNumbers = new ArrayList();
        for (int number : numbers) {
            lottoNumbers.add(LottoNumber.valueOf(number));
        }
        return lottoNumbers;
    }

    @ParameterizedTest
    @CsvSource(value = {"1000:1", "6000:6", "5000:5", "12000:12"}, delimiter = ':')
    @DisplayName("로또를 구매하면 구매가격만큼 컬렉션에서 로또개수를 조회")
    void test_that_throw_exception_when_number_is_outofrange(int price, int count) {
        //given
        LottoMoney lottoMoney = new LottoMoney(price);
        Lottos lottos = new Lottos(LottoFactory.createLottos(lottoMoney));
        LottoMachine lottoMachine = new LottoMachine(lottoMoney,lottos);

        //when
        LottosNumberDto lottosNumberDto = lottoMachine.getLottoNumber();

        //then
        assertThat(lottosNumberDto.getLottosNumber()).hasSize(count);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("당첨된 번호가 없는 경우 수익률은 0")
    void test_that_it_returns_zero_rate_if_winning_number_nonematch(int count) {
        //given
        List<Lotto> lottoList = new ArrayList();
        for (int i = 0; i < count; i++) {
            lottoList.add(new Lotto(getLottoNumbers(1, 2, 3, 14, 15, 16)));
        }
        LottoMoney lottoMoney = new LottoMoney(14000);
        Lottos lottos = new Lottos(lottoList);
        LottoMachine lottoMachine = new LottoMachine(lottoMoney,lottos);
        List<LottoNumber> winningNumbers = Arrays.stream(new int[]{11, 22, 23, 24, 25, 26})
                .boxed()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());

        //when
        LottoResultDto lottoResultDto = lottoMachine.getLottoResult(winningNumbers);

        //then
        assertThat(lottoResultDto.getPriceRatio()).isEqualTo((Rank.MISS.getWinningPrice() * count) / (double) 14000);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("5등 당첨된 경우 수익률은  ( 5등상금 * 당첨된 로또개수)/구입금액")
    void test_that_it_returns_winning_rate_if_winning_number_match_at5(int count) {
        //given
        List<Lotto> lottoList = new ArrayList();
        for (int i = 0; i < count; i++) {
            lottoList.add(new Lotto(getLottoNumbers(1, 2, 3, 14, 15, 16)));
        }
        LottoMoney lottoMoney = new LottoMoney(14000);
        Lottos lottos = new Lottos(lottoList);
        LottoMachine lottoMachine = new LottoMachine(lottoMoney,lottos);
        List<LottoNumber> winningNumbers = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6}).boxed()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());

        //when
        LottoResultDto lottoResultDto = lottoMachine.getLottoResult(winningNumbers);

        //then
        assertThat(lottoResultDto.getPriceRatio()).isEqualTo((Rank.FIFTH.getWinningPrice() * count) / (double) 14000);

    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("4등 당첨된 경우 수익률은  ( 4등상금 * 당첨된 로또개수)/구입금액")
    void test_that_it_returns_winning_rate_if_winning_number_match_at4(int count) {
        //given
        List<Lotto> lottoList = new ArrayList();
        for (int i = 0; i < count; i++) {
            lottoList.add(new Lotto(getLottoNumbers(1, 2, 3, 14, 15, 16)));
        }
        LottoMoney lottoMoney = new LottoMoney(14000);
        Lottos lottos = new Lottos(lottoList);
        LottoMachine lottoMachine = new LottoMachine(lottoMoney,lottos);
        List<LottoNumber> winningNumbers = Arrays.stream(new int[]{1, 2, 3, 14, 5, 6})
                .boxed()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());

        //when
        LottoResultDto lottoResultDto = lottoMachine.getLottoResult(winningNumbers);

        //then
        assertThat(lottoResultDto.getPriceRatio()).isEqualTo((Rank.FOURTH.getWinningPrice() * count) / (double) 14000);

    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("3등 당첨된 경우 수익률은  ( 3등상금 * 당첨된 로또개수)/구입금액")
    void test_that_it_returns_winning_rate_if_winning_number_match_at3(int count) {
        //given
        List<Lotto> lottoList = new ArrayList();
        for (int i = 0; i < count; i++) {
            lottoList.add(new Lotto(getLottoNumbers(1, 2, 3, 14, 15, 16)));
        }
        LottoMoney lottoMoney = new LottoMoney(14000);
        Lottos lottos = new Lottos(lottoList);
        LottoMachine lottoMachine = new LottoMachine(lottoMoney,lottos);
        List<LottoNumber> winningNumbers = Arrays.stream(new int[]{1, 2, 3, 14, 15, 6})
                .boxed()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());

        //when
        LottoResultDto lottoResultDto = lottoMachine.getLottoResult(winningNumbers);

        //then
        assertThat(lottoResultDto.getPriceRatio()).isEqualTo((Rank.THIRD.getWinningPrice() * count) / (double) 14000);

    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("1등 당첨된 경우 수익률은  ( 1등상금 * 당첨된 로또개수)/구입금액")
    void test_that_it_returns_winning_rate_if_winning_number_match(int count) {
        //given
        List<Lotto> lottoList = new ArrayList();
        for (int i = 0; i < count; i++) {
            lottoList.add(new Lotto(getLottoNumbers(1, 2, 3, 14, 15, 16)));
        }
        LottoMoney lottoMoney = new LottoMoney(14000);
        Lottos lottos = new Lottos(lottoList);
        LottoMachine lottoMachine = new LottoMachine(lottoMoney,lottos);
        List<LottoNumber> winningNumbers = Arrays.stream(new int[]{1, 2, 3, 14, 15, 16})
                .boxed()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());

        //when
        LottoResultDto lottoResultDto = lottoMachine.getLottoResult(winningNumbers);

        //then
        assertThat(lottoResultDto.getPriceRatio()).isEqualTo((Rank.FIRST.getWinningPrice() * count) / (double) 14000);

    }

}
