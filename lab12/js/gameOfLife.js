

function gameOfLife(arr){

    var numberOfNeight = 0;


    for(var i=0; i<9; i++){

        if(i==4) continue;

        if(arr[i]==1){
            numberOfNeight++;
        }
    }

    if(arr[4]==0){ //martwa

        if(numberOfNeight==3) return true;
        return false;
    } 
    else{//zywa
        if(numberOfNeight<2){
            return false;
        }
        if(numberOfNeight>3){
            return false;
        }
        return true;

    }
}


