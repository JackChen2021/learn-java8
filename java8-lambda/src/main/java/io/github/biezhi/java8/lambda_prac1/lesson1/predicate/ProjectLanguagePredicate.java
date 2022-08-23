package io.github.biezhi.java8.lambda_prac1.lesson1.predicate;

import io.github.biezhi.java8.lambda_prac1.lesson1.Project;
import io.github.biezhi.java8.lambda_prac1.lesson1.ProjectPredicate;

/**
 * 按编程语言过滤
 *
 * @author biezhi
 * @date 2018/2/9
 */
public class ProjectLanguagePredicate implements ProjectPredicate {

    private String language;

    public ProjectLanguagePredicate(String language) {
        this.language = language;
    }

    @Override
    public boolean test(Project project) {
        return language.equals(project.getLanguage());
    }
}
