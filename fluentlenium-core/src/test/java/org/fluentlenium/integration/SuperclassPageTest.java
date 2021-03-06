/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package org.fluentlenium.integration;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentWebElement;
import org.fluentlenium.integration.localtest.LocalFluentCase;
import org.junit.ComparisonFailure;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.fest.assertions.Assertions.assertThat;

public class SuperclassPageTest extends PageTest {

    @Page AnotherPage anotherPage;
    @Page AnotherPage page2;

    @Test
    public void checkGoToPagesDeclaredInThisClassAndSuperclass() {
        page.go();
        assertThat(title()).contains("Selenium");
        anotherPage.go();
        assertThat(title()).contains("Another Page");
    }

    @Test
    public void checkGoToPagesOverridingPageDeclaredInSuperclass() {
        System.out.println(page2.getClass());
        page2.go();
        assertThat(title()).contains("Another Page");
    }
}

class AnotherPage extends FluentPage {

    @Override
    public String getUrl() {
        return LocalFluentCase.DEFAULT_URL + "anotherpage.html";
    }

    @Override
    public void isAt() {
        assertThat($("title").first().getText()).isEqualTo("Another Page");
    }
}
