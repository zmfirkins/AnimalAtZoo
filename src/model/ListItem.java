package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="items")
public class ListItem {

@Id
@GeneratedValue
@Column(name="ID")
private int id;
@Column(name="ANIMAL")
private
String animal;
@Column(name="ITEM")
private
String item;

public ListItem(){
	
}

public ListItem
(String animal, String item){
this.animal = animal;
this.item = item;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getAnimal() {
	return animal;
}
public void setAnimal(String animal) {
	this.animal = animal;
}
public String getItem() {
	return item;
}
public void setItem(String item) {
	this.item = item;
}

public String returnItemDetails( ) {
return this.animal + ": " +this.item;
}

}
