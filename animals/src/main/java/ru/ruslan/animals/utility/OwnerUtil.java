package ru.ruslan.animals.utility;

import lombok.experimental.UtilityClass;
import ru.ruslan.animals.model.Owner;

@UtilityClass
public class OwnerUtil {
    public String getFullName(Owner owner) {
        String name = owner.getName();
        String surname = owner.getSurname();
        return name + " " + surname;
    }
}
