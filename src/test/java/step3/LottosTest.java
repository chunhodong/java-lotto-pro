package step3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import step3.model.Lotto;
import step3.model.Lottos;
import step3.model.Rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    @Test
    @DisplayName("로또를 추가하면 생성된 로또번호를 조회할 수 있다")
    void test_that_throw_exception_when_number_is_outofrange(){
        //given
        Lottos lottos = new Lottos(new ArrayList());

        //when
        lottos.addByAuto();

        //then
        assertThat(lottos.getNumbersOfLottos()).hasSize(1);
        assertThat(lottos.getNumbersOfLottos().get(0)).hasSize(6);
        assertThat(lottos.getNumbersOfLottos().get(0)).hasSize(6);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    @DisplayName("로또를 추가하면 구매가격의 총합은 구매개수 * 1000")
    void test_that_it_returns_sum_of_price(int count){
        //given
        Lottos lottos = new Lottos(new ArrayList());

        //when
        for(int i = 0; i < count; i++) {
            lottos.addByAuto();
        }
        //then
        assertThat(lottos.getSumOfPriceLottos()).isEqualTo(count * 1000);

    }

    @Test
    @DisplayName("5등이 당첨될 경우 당첨됫 횟수를 조회")
    void test_that_it_returns_count_of_winning_if_5rd(){
        //given
        List<Lotto> lottoNumbers = new ArrayList();
        lottoNumbers.add(new Lotto(Arrays.asList(1,2,3,14,15,16)));
        lottoNumbers.add(new Lotto(Arrays.asList(2,11,3,4,1,6)));
        lottoNumbers.add(new Lotto(Arrays.asList(1,11,33,2,35,3)));

        Lottos lottos = new Lottos(lottoNumbers);

        //when
        Map<Rank,Integer> rankStats = lottos.getRankStatsOfLottos(Arrays.asList(1,2,3,43,44,45));

        //then
        assertThat(rankStats.get(Rank.FIFTH)).isEqualTo(3);
    }

    @Test
    @DisplayName("4등이 당첨될 경우 당첨됫 횟수를 조회")
    void test_that_it_returns_count_of_winning_if_4rd(){
        //given
        List<Lotto> lottoNumbers = new ArrayList();
        lottoNumbers.add(new Lotto(Arrays.asList(1,2,3,14,15,16)));
        lottoNumbers.add(new Lotto(Arrays.asList(2,11,3,4,15,1)));
        lottoNumbers.add(new Lotto(Arrays.asList(1,11,33,2,15,3)));

        Lottos lottos = new Lottos(lottoNumbers);

        //when
        Map<Rank,Integer> rankStats = lottos.getRankStatsOfLottos(Arrays.asList(1,2,3,43,15,45));

        //then
        assertThat(rankStats.get(Rank.FOURTH)).isEqualTo(3);
    }

    @Test
    @DisplayName("3등이 당첨될 경우 당첨됫 횟수를 조회")
    void test_that_it_returns_count_of_winning_if_3rd(){
        //given
        List<Lotto> lottoNumbers = new ArrayList();
        lottoNumbers.add(new Lotto(Arrays.asList(1,2,3,14,15,16)));
        lottoNumbers.add(new Lotto(Arrays.asList(2,11,3,4,1,6)));
        lottoNumbers.add(new Lotto(Arrays.asList(1,11,33,2,35,3)));

        Lottos lottos = new Lottos(lottoNumbers);

        //when
        Map<Rank,Integer> rankStats = lottos.getRankStatsOfLottos(Arrays.asList(1,2,3,43,44,45));

        //then
        assertThat(rankStats.get(Rank.FIFTH)).isEqualTo(3);
    }

    @Test
    @DisplayName("1등이 당첨될 경우 당첨됫 횟수를 조회")
    void test_that_it_returns_count_of_winning_if_1rd(){
        //given
        List<Lotto> lottoNumbers = new ArrayList();
        lottoNumbers.add(new Lotto(Arrays.asList(1,2,3,14,15,16)));
        lottoNumbers.add(new Lotto(Arrays.asList(2,15,3,14,1,16)));
        lottoNumbers.add(new Lotto(Arrays.asList(1,16,15,2,14,3)));

        Lottos lottos = new Lottos(lottoNumbers);

        //when
        Map<Rank,Integer> rankStats = lottos.getRankStatsOfLottos(Arrays.asList(1,2,3,14,15,16));

        //then
        assertThat(rankStats.get(Rank.FIRST)).isEqualTo(3);
    }


}
