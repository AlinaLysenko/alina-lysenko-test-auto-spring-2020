package hwAPI.assertions;

import hwAPI.entity.data.InputTextData;
import hwAPI.entity.response.YandexSpellerResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class YandexSpellerAssertions {

    private List<YandexSpellerResponse> result;
    private InputTextData excpectedData;

    public YandexSpellerAssertions(List<YandexSpellerResponse> result, InputTextData excpectedData) {
        this.result = result;
        this.excpectedData = excpectedData;
    }

    public YandexSpellerAssertions isAllErrorsFound() {
        assertThat(result.size() == excpectedData.getErrors().size());
        return this;
    }

    public YandexSpellerAssertions assertErrorCode() {
        for (int i = 0; i < excpectedData.getErrors().size(); i++) {
            assertThat(result.get(i).getCode())
                    .isEqualTo(excpectedData.getErrors().get(i).getCode().getValue());
        }
        return this;
    }

    public YandexSpellerAssertions assertSuggestions() {
        for (int i = 0; i < excpectedData.getErrors().size(); i++) {
            assertThat(result.get(i).getS().contains(excpectedData.getErrors().get(i).getSuggestedWord()));
        }
        return this;
    }

    public YandexSpellerAssertions assertMisspelledWord() {
        for (int i = 0; i < excpectedData.getErrors().size(); i++) {
            assertThat(result.get(i).getWord()).isEqualTo(excpectedData.getErrors().get(i).getExpectedMistakes());
        }
        return this;
    }

    public YandexSpellerAssertions isEmptyResponse() {
        assertThat(result).isEmpty();
        return this;
    }

    public YandexSpellerAssertions isNotEmptyResponse() {
        assertThat(result).isNotEmpty();
        return this;
    }
}
