
//global variables
const imageWidth = 500;
const imageHeight = 600;
let currentImage = null;

/**
 * This method is activated whenever the button "generate" is clicked.
 * It retrieves the input value from the input field.
 * An HTTP request is generated with the user input as a search term for the query parameter of the server.
 * It waits for the request to complete and the response to be processed to create a JSON object.
 * From this JSON object, an image is created and the author credited. 
 * The function calls other functions as required to handle the request.
 * Finally, the client view is updated to display the image and the author.
 * */
const handleRequest = async () => {

    const textfield = document.getElementById("user_input").value;
    const json = await fetch("image?query=" + encodeURIComponent(textfield))
        .then(response => response.json())
        .then(data => data);

    const image = createImage(json.image_url);
    const a = creditAuthor(json.photographer_url, json.photographer);

    updateImage(image);
    const credit_author = document.getElementById("credit_author");
    credit_author.innerHTML = "This photo was taken by " + a;

}

/**
 * This is a method to update the image on the portrait. The image is a global variable which is set first to null.
 * Once a user requests an image, it will check whether there is already an image on portrait. If so, it will replace the image
 * and store the value of the new image in global variable. Otherwise, it simply adds the image, then adds it to global variable.
 * @param {Image} image - An Image() object. 
 */
const updateImage = (image) => {
    const picture_area = document.getElementById("picture_area");

    if (currentImage != null) {
        picture_area.removeChild(currentImage);
        picture_area.appendChild(image);
        currentImage = image;
    }

    else {
        picture_area.appendChild(image);
        currentImage = image;
    }
}

/**
 * Creates a new Image() object from a provided URL.
 * @param {string} url - image URL
 * @return {Image} - Image object.
 */
const createImage = (url) => {
    const image = new Image(imageWidth, imageHeight);
    image.src = url;
    return image;
}

/**
 * Creates a <a> element with <href> atttribute set to URL and inner HTML to name. Returns the element.
 * It is used to create a link to credit the author of the picture with their website page.
 * @param {string} url - The url link to photographer webstie.
 * @param {string} name - The name of the photographer.
 * @return {outerHTML} - element property.
 */
const creditAuthor = (url, name) => {
    const a = document.createElement("a");
    a.href = url;
    a.innerHTML = name;
    return a.outerHTML;
}