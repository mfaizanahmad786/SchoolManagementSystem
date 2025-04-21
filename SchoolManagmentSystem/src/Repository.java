import java.io.Serializable;
import java.util.ArrayList;

public class Repository<T> implements Serializable {
    private ArrayList<T> items;

    public Repository() {
        items = new ArrayList<T>();
    }

    public ArrayList<T> getItems() {
        return items;
    }

    public void add(T item){
        items.add(item);
        System.out.println(item.getClass().getSimpleName()+" Added To Repository");
    }

    public void remove(T item){
        if(items.contains(item)){
            items.remove(item);
            System.out.println(item.getClass().getSimpleName()+" Removed From Repository");
        }else{
            System.out.println(item.getClass().getSimpleName()+" Not Found in Repository");
        }
    }

    public ArrayList<T> getAll(){
        return items;
    }

    public void searchStudentByName(String name){
        boolean found = false;
        for(T item:items){
            if(item instanceof Student){
                Student student = (Student) item;
                if(student.getName().equals(name)){
                    student.displayDetails();
                    found = true;
                }
            }
        }
        if(!found){
            System.out.println("Student Does not Exist");
        }
    }

}
