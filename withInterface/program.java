package withInterface;
import java.util.HashSet;

public class program {
    public static void main(String[] args) {
        Integer level = 0;

        individual Oleg = new individual("Oleg", "Brown", 80, Gender.MALE);
        individual billianna = new individual("Billianna", null, 80, Gender.FEMALE);

        individual anna = new individual("Anna", null, 62, Gender.FEMALE);
        individual Mike = new individual("Mike", "String", 62, Gender.MALE);
        individual Jane = new individual("Jane", "Brown", 62, Gender.FEMALE);
        individual Dane = new individual("Dane", "Brown", 62, Gender.MALE);

        individual David = new individual("David", "String", 44, Gender.MALE);
        individual charlie = new individual("Charlie", null, 44, Gender.MALE);

        individual Olga = new individual("Olga", "Brown", 70, Gender.FEMALE);

        individual david = new individual("Davidinny", null, 26, Gender.MALE);

        individual Vektor = new individual("Vektor", null, 26, Gender.MALE);
        individual Mary = new individual("Mary", null, 26, Gender.FEMALE);

        individual Gregory = new individual("Gregory", null, 8, Gender.MALE);
        individual Timmy = new individual("Timmy", null, 44, Gender.MALE);
        individual Lola = new individual("Lola", null, 11, Gender.FEMALE);

        FamilyTree treeOne = new Tree();

        // поженились
        treeOne.addSpouse(billianna, Oleg); 

        // нарожали детей
        treeOne.addChild(billianna, anna); // неважно кого указать родителем, если он замужем, второй родитель автоматом становится родителем
        treeOne.addChild(Oleg, Jane);

        // дети поженились
        treeOne.addSpouse(anna, Mike); 
        treeOne.addSpouse(Jane, Dane);

        // у детей появились дети
        treeOne.addChild(anna, David);
        treeOne.addChild(anna, charlie);
        treeOne.addChild(Dane, Timmy);

        // родились внебрачные дети
        treeOne.addChild(charlie, david);
        treeOne.addChild(David, Vektor);
        treeOne.addChild(Timmy, Lola);

        // Vector женился
        treeOne.addSpouse(Vektor, Mary);

        // у Vector родился ребенок
        treeOne.addChild(Vektor, Gregory);
   
    // создание комода
    Commode firstCommode = new Commode(01, "black");
    Commode twoCommode = new Commode(02, "white");
    
    // действия персонажей с комодом
    OpenDrawer open1 = new OpenDrawer(anna, Drawers.one, firstCommode);
    OpenDrawer open2 = new OpenDrawer(Olga, Drawers.two, firstCommode);
    OpenDrawer open3 = new OpenDrawer(Oleg, Drawers.three, twoCommode);

    // взаиодействие с ящиками
    System.out.println(open1); 
    System.out.println(open2);
    System.out.println(open3);

    // сохранение в файл семейных связей
    FileSaverInterface fileSaver = new SaveToTxt(treeOne);

    fileSaver.saveToFile("family-tree");
    
    // вывод в консоль
    treeOne.printFamilyTree(billianna, new HashSet<>(), level); // семья Billiann
    // treeOne.printFamilyTree(Mike, new HashSet<>(), level); // семья Mike
    // treeOne.printFamilyTree(Dane, new HashSet<>(), level); // семья Dane
    }
    
}
