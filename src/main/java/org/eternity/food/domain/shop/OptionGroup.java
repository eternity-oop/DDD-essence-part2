package org.eternity.food.domain.shop;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.eternity.base.domain.DomainEntity;
import org.eternity.food.domain.shop.OptionGroupId.MenuOptionGroupIdJavaType;
import org.hibernate.annotations.JavaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
public class OptionGroup extends DomainEntity<OptionGroup, OptionGroupId> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JavaType(MenuOptionGroupIdJavaType.class)
    private OptionGroupId id;

    @Column(name="NAME")
    private String name;

    @Column(name="MANDATORY")
    private boolean mandatory;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="OPTION", joinColumns = @JoinColumn(name="OPTION_GROUP_ID"))
    private List<Option> options = new ArrayList<>();

    public OptionGroup(String name, boolean mandatory, Option options) {
        this(null, name, mandatory, Arrays.asList(options));
    }

    @Builder
    public OptionGroup(OptionGroupId id, String name, boolean mandatory, List<Option> options) {
        this.id = id;
        this.mandatory = mandatory;
        setName(name);
        setOptions(options);
    }

    OptionGroup() {
    }

    private void setName(String name) {
        if (name == null || name.length() < 2) {
            throw new IllegalArgumentException("옵션그룹명은 길이는 최소 2글자 이상이어야 합니다.");
        }

        this.name = name;
    }

    private void setOptions(List<Option> options) {
        if (options == null || options.size() < 1) {
            throw new IllegalArgumentException("옵션의 길이는 최소 1개 이상이어야 합니다.");
        }

        this.options = options;
    }

    public Optional<Option> findOption(Option target) {
        return options.stream().filter(option -> option.equals(target)).findFirst();
    }

    public boolean isFree() {
        return options.stream().allMatch(Option::isFree);
    }

    public void chaneName(String name) {
        this.name = name;
    }

    public void changeOptionName(Option target, String optionName) {
        Option option = options.stream().filter(each -> each.equals(target)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
        options.remove(option);
        options.add(target.changeName(optionName));
    }
}
