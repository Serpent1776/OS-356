public class MemoryStorage {
    int totalmemory;
    MemoryBlock[] fiveblocks;
    MemoryBlock[] tenblocks;
    MemoryBlock[] twentyblocks;
    
    CatchAllMemoryStorage catchAll;

   public MemoryStorage(int totalmemory, int fiveblockslen, int tenblockslen, int twentyblockslen) {
        this.totalmemory = totalmemory;
        this.fiveblocks = new MemoryBlock[fiveblockslen];
        this.tenblocks = new MemoryBlock[tenblockslen];
        this.twentyblocks = new MemoryBlock[twentyblockslen];
        this.catchAll = new CatchAllMemoryStorage(totalmemory - (fiveblockslen*5 + tenblockslen*10 + twentyblockslen*20));
   }
   /*
    * allocate() allocates a memory block to an array.
    */
   public void allocate(MemoryBlock mem) throws MemoryException {
     boolean stored = false;
     switch (mem.getBytes()) {
          case 5:
          for(int i = 0; i < fiveblocks.length; i++) {
               if(fiveblocks[i] == null) {
                fiveblocks[i] = mem;
                stored = true;
                break;
               } 
           }  
           if(!stored) { 
               for(int i = 0; i < tenblocks.length; i++) {
                    if(tenblocks[i] == null) {
                     tenblocks[i] = mem;
                     stored = true;
                     break;
                    } 
                }
                if(!stored) {
                    for(int i = 0; i < twentyblocks.length; i++) {
                         if(twentyblocks[i] == null) {
                          twentyblocks[i] = mem;
                          stored = true;
                          break;
                         } 
                     } 
                if (!stored) {try {
                    catchAll.add(mem); 
                } catch (MemoryException meme) {throw new MemoryException("The Memory is full!");}
           }
          }
     }
          break;
          case 10:
          for(int i = 0; i < tenblocks.length; i++) {
               if(tenblocks[i] == null) {
                tenblocks[i] = mem;
                stored = true;
                break;
               } 
           }   
           if(!stored) { 
                     try {
                    catchAll.add(mem); 
                } catch (MemoryException meme) {
                    for(int i = 0; i < twentyblocks.length; i++) {
                         if(twentyblocks[i] == null) {
                          twentyblocks[i] = mem;
                          stored = true;
                          break;
                         } 
                     } 
               if(!stored) {throw new MemoryException("The Memory is full!");}
               }
           }     
          break;
          case 20:
          for(int i = 0; i < twentyblocks.length; i++) {
               if(twentyblocks[i] == null) {
                twentyblocks[i] = mem;
                stored = true;
                break;
               } 
           }  
           if(!stored) { 
               try {
               catchAll.add(mem); 
           } catch (MemoryException meme) {
               throw new MemoryException("The Memory is full!");
           }
          }  
          break;
     
          default:
          try {
              catchAll.add(mem); 
          } catch (MemoryException meme) {
               if(mem.getBytes() < 5) {
                    for(int i = 0; i < fiveblocks.length; i++) {
                         if(fiveblocks[i] == null) {
                          fiveblocks[i] = mem;
                          stored = true;
                          break;
                         } 
                     }  
                    if (!stored) {
                         for(int i = 0; i < tenblocks.length; i++) {
                              if(tenblocks[i] == null) {
                               tenblocks[i] = mem;
                               stored = true;
                               break;
                              } 
                          }
                         if(!stored) {
                              for(int i = 0; i < twentyblocks.length; i++) {
                                   if(twentyblocks[i] == null) {
                                    twentyblocks[i] = mem;
                                    stored = true;
                                    break;
                                   } 
                               } 
                               if(!stored) {
                                   throw new MemoryException("The Memory is full!");
                               }   
                          }
                    } 
               } else if (mem.getBytes() < 10) {
                    for(int i = 0; i < tenblocks.length; i++) {
                         if(tenblocks[i] == null) {
                          tenblocks[i] = mem;
                          stored = true;
                          break;
                         } 
                     }
                    if(!stored) {
                         for(int i = 0; i < twentyblocks.length; i++) {
                              if(twentyblocks[i] == null) {
                               twentyblocks[i] = mem;
                               stored = true;
                               break;
                              } 
                          }
                          if(!stored) {
                              throw new MemoryException("Memory block could not be stored!");
                          } 
                    }       
               } else if (mem.getBytes() < 20) {
                    for(int i = 0; i < twentyblocks.length; i++) {
                         if(twentyblocks[i] == null) {
                          twentyblocks[i] = mem;
                          stored = true;
                          break;
                         } 
                     }
                     if(!stored) {
                         throw new MemoryException("Memory block could not be stored!");
                     }  
               } else {
                    throw new MemoryException("Block is unable to be stored!");
               }
          }
               break;
     }
   }
   /*
    * free() searches from the block with the name of the provided string s and then removes it.
    */
   public void free(String s) throws MemoryException {
     boolean found = false;
          for(int i = 0; i < fiveblocks.length; i++) {
          try {
               if(fiveblocks[i].getName().equals(s)) {
               fiveblocks[i] = null;
               found = true;
               break;
               }
          } catch (Exception e) {
               
          }
     }
     if(!found) {
          for(int i = 0; i < tenblocks.length; i++) {
          try {
               if(tenblocks[i].getName().equals(s)) {
                    tenblocks[i] = null;
                    found = true;
                    break;
               }
          } catch (Exception e) {
                   
               }
          }
     }
     if(!found) {
          for(int i = 0; i < twentyblocks.length; i++) {
          try {
               if(twentyblocks[i].getName().equals(s)) {
                    twentyblocks[i] = null;
                    found = true;
                    break;
               }
          } catch (Exception e) {
                    
               }
          }   
     }
     if(!found) {try{catchAll.remove(s);} catch (MemoryException meme) {throw meme;}}
     }
     @Override
     public String toString() {
        String memoryStr = "";
        String five = "5: ";
        String ten = "10: ";
        String twenty = "20: ";
        String starDash = "";
        String catchMemStr = catchAll.toString(); 

        for(int i = 0; i < fiveblocks.length; i++) {
          if(fiveblocks[i] != null) {
               five += fiveblocks[i].getName() + " ";
               starDash += "*****";
          } else {
               five += "- ";
               starDash += "-----";
          }
        }
        for(int i = 0; i < tenblocks.length; i++) {
          if(tenblocks[i] != null) {
               ten += tenblocks[i].getName() + " ";
               starDash += "**********";
          } else {
               ten += "- ";
               starDash += "----------";
          }
        }
        for(int i = 0; i < twentyblocks.length; i++) {
          if(twentyblocks[i] != null) {
               twenty += twentyblocks[i].getName() + " ";
               starDash += "********************";
          } else {
               twenty += "- ";
               starDash += "--------------------";
          }
        }
        starDash += catchAll.memoryListStarDash();
        memoryStr += five + "\n" + ten + "\n" + twenty + "\n" + catchMemStr + "\n" + starDash;
        return memoryStr;
     }
}