package withInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Tree implements FamilyTree {
    private Map<PersonInterface, List<PersonInterface>> parentChildMapping;

    private Map<PersonInterface, PersonInterface> spouseMapping;

    public Tree() {
        parentChildMapping = new HashMap<>();
        spouseMapping = new HashMap<>();
    }

    @Override
    public void addParent(PersonInterface child, PersonInterface parent) {
        List<PersonInterface> children = parentChildMapping.getOrDefault(parent, new ArrayList<>());
        children.add(child);
        parentChildMapping.put(parent, children);
    }

    @Override
    public void addChild(PersonInterface parent, PersonInterface child) {
        List<PersonInterface> children = parentChildMapping.getOrDefault(parent, new ArrayList<>());
        children.add(child);
        parentChildMapping.put(parent, children);

        // проверяем, есть ли у родителя супруг
        PersonInterface spouse = spouseMapping.get(parent);
        if (spouse != null) {
            List<PersonInterface> spouseChildren = parentChildMapping.getOrDefault(spouse, new ArrayList<>());
            spouseChildren.add(child);
            parentChildMapping.put(spouse, spouseChildren);
        }
    }

    @Override
    public List<PersonInterface> getChildren(PersonInterface parent) {
        return parentChildMapping.get(parent);
    }

    @Override
    public List<PersonInterface> getParents(PersonInterface child) {
        List<PersonInterface> parents = new ArrayList<>();
        for (Map.Entry<PersonInterface, List<PersonInterface>> entry : parentChildMapping.entrySet()) {
            if (entry.getValue().contains(child)) {
                parents.add(entry.getKey());
            }
        }
        return parents;
    }

    @Override
    public void printFamilyTree(PersonInterface person, HashSet<PersonInterface> printed, int depth) {
        if (printed.contains(person)) {
            return;
        }

        printed.add(person);

        String tabs = "";
        for (int i = 0; i < depth; i++) {
            tabs += "\t";
        }
        if (depth < 1) {
            System.out.println(
                    "Family tree:\n" + tabs + person.getName() + " " + person.getAge() + " - we start with it");
        }
        if (depth > 0) {
            if (person.getSex() == Gender.MALE) {
                System.out.println(tabs + person.getName() + " " + person.getAge() + " - son");
            }
            if (person.getSex() == Gender.FEMALE) {
                System.out.println(tabs + person.getName() + " " + person.getAge() + " - daughter");
            }
        }

        if (person.getAge() < 18) { // если возраст меньше 18 лет, выводим сообщение о том, что он еще ребенок
            if (person.getSex() == Gender.MALE) {
                System.out.println(tabs + "what kind of spouse is he, he is still a child");
            } else {
                System.out.println(tabs + "what kind of spouse is she, she is still a child");
            }
        } else if (!printed.contains(spouseMapping.get(person)) && spouseMapping.get(person) != null) {
            PersonInterface spouseName = spouseMapping.get(person);
            printed.add(spouseName);

            if (person.getSex() == Gender.MALE) {
                System.out.println(tabs + spouseName + " " + spouseName.getAge() + " - his WIFE");
            } else if (person.getSex() == Gender.FEMALE) {
                System.out.println(tabs + spouseName + " " + spouseName.getAge() + " - her HUSBAND");
            }
        } else if (spouseMapping.get(person) == null) {
            System.out.println(tabs + "no spouse");
        }

        List<PersonInterface> children = getChildren(person);
        if (children != null) {
            for (PersonInterface child : children) {
                printFamilyTree(child, printed, depth + 1);
            }
        }

        List<PersonInterface> parents = getParents(person);
        if (parents != null) {
            for (PersonInterface parent : parents) {
                printFamilyTree(parent, printed, depth - 1);
            }
        }
    }

    @Override
    public void addSpouse(PersonInterface person1, PersonInterface person2) {
        spouseMapping.put(person1, person2);
        spouseMapping.put(person2, person1);
    }

    public PersonInterface getSpouses(PersonInterface person) {
        if (spouseMapping.containsKey(person)) {
            return spouseMapping.get(person);
        } else {
            return null; // Если у человека нет супруга
        }
    }

    public Map<PersonInterface, List<PersonInterface>> getParentChildMapping() {
        return parentChildMapping;
    }

    public Map<PersonInterface, PersonInterface> getSpouseMapping() {
        return spouseMapping;
    }

}
