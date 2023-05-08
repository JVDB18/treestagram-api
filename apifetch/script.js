
    const myInit = {
        method: "GET"
      };
      let tab = [];

(function(){
    for(i = 0; i < 10; i++){
        let random = Math.floor(Math.random()*100);
        fetch("https://picsum.photos/id/"+ random + "/200/300", myInit).then(response =>{
          tab.push(response.url);
          let img = document.createElement("img");
          img.src= response.url;
          document.body.appendChild(img);
        });
    }
    console.log(tab)
}
());