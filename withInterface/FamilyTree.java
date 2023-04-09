package withInterface;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public interface FamilyTree {
    void addParent(PersonInterface child, PersonInterface parent);
    void addChild(PersonInterface parent, PersonInterface child);
    void addSpouse(PersonInterface person1, PersonInterface person2);
    void printFamilyTree(PersonInterface person, HashSet<PersonInterface> printed, int depth);

    PersonInterface getSpouses(PersonInterface person);

    List<PersonInterface> getChildren(PersonInterface parent);
    List<PersonInterface> getParents(PersonInterface child);

    Map<PersonInterface, PersonInterface> getSpouseMapping();
    Map<PersonInterface, List<PersonInterface>> getParentChildMapping();
    
}
