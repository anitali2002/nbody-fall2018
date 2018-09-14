public class SimpleWordGame {
  public int points(String[] player, String[] dictionary) {
    // you write code here
    
    int points = 0;
    
    //create a new HashMap to keep track of the player's words that are in the dictionary
    Map<String, Integer> common = new HashMap<String, Integer>();
    
    //loop through the player's words and adds them to the HashMap
    for (String word : player){
      if (dictionary.includes(word)){
        if (!common.containsKey(word)){
          common.put(word, (word.length() * 2));
        }
      }
    }
    
    //loop through the HashSet and add up all the points
    for (int value : common.values()){
      points += value;
    }
  }
}