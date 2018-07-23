package room;


public class room {

        private String room;
        private int num;
        private int money;
        private int is;
        public int getIs(){
            return is;
        }
        public  void setIs(int is){
            this.is=is;
        }
        public String getRoom() {
            return room;
        }
        public void setRoom(String room) {
            this.room = room;
        }
        public int getMoney() {
        return money;
        }
         public void setMoney(int money) {
         this.money  = money;
        }
        public int getNum() {
        return num;
        }
        public void setNum(int num) {
        this.num  = num;
        }

        public room(String room, int num, int money,int is) {
            super();
            this.room = room;
            this.num = num;
            this.money = money;
            this.is=is;
        }
}
