package ir.maktab.base;

public abstract class Menu {
   protected int option;
   public abstract void display();
   public abstract void menuHandler();
   public abstract boolean checkChoice();

   public int getOption() {
      return option;
   }

   public void setOption(int option) {
      this.option = option;
   }
}
