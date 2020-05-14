package Project2.GhostKitchen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    MenuItemRepository menuItemRepository;
    @Autowired
     CartRepository cart;

    //home page
    @RequestMapping("/")
    public String home(){
        return "home";
    }

    //user home page
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userHome(){
        return "user";
    }

    //login page
    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public String loginUserPage(Model model){
        model.addAttribute("givenAction","/user/login");
        return "loginUserPage";
    }

    //verify login info
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String loginUserPage(Model model, @RequestParam String uName, @RequestParam String psw){
        for ( User user : userRepository.findAll() ) {
            if( uName.equals(user.getUsername()) && psw.equals(user.getPassword())){
                return "redirect:/user/"+user.getId().toString();
            }
        }
        model.addAttribute("givenAction","/user/login");
        model.addAttribute("givenUsername",uName);
        model.addAttribute("givenPassword",psw);
        return "loginUserPageFailed";
    }

    // /register create new User
    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public String newUser(Model model) {
        model.addAttribute("givenAction", "/user/register");
        model.addAttribute("givenUsername", "");
        model.addAttribute("givenPassword", "");
        model.addAttribute("givenfName","");
        model.addAttribute("givenlName","");
        model.addAttribute("givenEmail","");
        model.addAttribute("givenStreet","");
        model.addAttribute("givenCity","");
        model.addAttribute("givenState","");
        model.addAttribute("givenZip","");
        return "registerUserPage";
    }

    // /register save new User
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String addUser(@RequestParam String uName, @RequestParam String psw, @RequestParam String fName,
                          @RequestParam String lName, @RequestParam String email, @RequestParam String street,
                          @RequestParam String city, @RequestParam String state, @RequestParam String zip) {
        User newUser = new User(uName, psw, fName, lName, email, street, city, state, zip);
        userRepository.save(newUser);
        return "redirect:/user/"+newUser.getId().toString();
    }

    // /User/id# shows UserPage
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable(value = "id") Long userId, Model model) {
        Optional<User> user = userRepository.findById(userId);
        model.addAttribute("user",user.get());
        return "userPage";
    }

    // /user/id#/edit displays edit user interface
    @RequestMapping(value = "/user/{id}/edit", method = RequestMethod.GET)
    public String editUser(@PathVariable(value = "id") Long userId, Model model) {
        Optional<User> user = userRepository.findById(userId);
        model.addAttribute("givenAction", "/user/"+user.get().getId()+"/edit");
        model.addAttribute("givenUsername", user.get().getUsername());
        model.addAttribute("givenPassword", user.get().getPassword());
        model.addAttribute("givenfName", user.get().getfName());
        model.addAttribute("givenlName", user.get().getlName());
        model.addAttribute("givenEmail", user.get().getEmail());
        model.addAttribute("givenStreet", user.get().getStreet());
        model.addAttribute("givenCity", user.get().getCity());
        model.addAttribute("givenState", user.get().getState());
        model.addAttribute("givenZip", user.get().getZip());
        return "editUserPage";
    }

    // /edit saves edits to user
    @RequestMapping(value = "/user/{id}/edit", method = RequestMethod.POST)
    public String editUser(@PathVariable(value = "id") Long userId,
                           @RequestParam String uName, @RequestParam String psw, @RequestParam String fName,
                           @RequestParam String lName, @RequestParam String email, @RequestParam String street,
                           @RequestParam String city, @RequestParam String state, @RequestParam String zip) {
        Optional<User> user = userRepository.findById(userId);
        user.get().setUsername(uName);
        user.get().setPassword(psw);
        user.get().setfName(fName);
        user.get().setlName(lName);
        user.get().setEmail(email);
        user.get().setStreet(street);
        user.get().setCity(city);
        user.get().setState(state);
        user.get().setZip(zip);
        userRepository.save(user.get());
        return "redirect:/user/"+user.get().getId().toString();
    }

    // /user/delete/id# deletes user by id
    @RequestMapping(value = "/user/{id}/delete")
    public String deleteUser(@PathVariable(value = "id") Long userId) {
        userRepository.deleteById(userId);
        return "redirect:/user";
    }

    //restaurant home page
    @RequestMapping(value = "/restaurant", method = RequestMethod.GET)
    public String restaurantHome(){
        return "restaurant";
    }

    //restaurant login page
    @RequestMapping(value = "/restaurant/login", method = RequestMethod.GET)
    public String loginRestaurantPage(Model model){
        model.addAttribute("givenAction","/restaurant/login");
        return "loginRestaurantPage";
    }

    //verify login info
    @RequestMapping(value = "/restaurant/login", method = RequestMethod.POST)
    public String loginRestaurantPage(Model model, @RequestParam String uName, @RequestParam String psw){
        for ( Restaurant restaurant : restaurantRepository.findAll() ) {
            if( uName.equals(restaurant.getUsername()) && psw.equals(restaurant.getPassword())){
                return "redirect:/restaurant/"+restaurant.getId().toString();
            }
        }
        model.addAttribute("givenAction","/restaurant/login");
        model.addAttribute("givenUsername",uName);
        model.addAttribute("givenPassword",psw);
        return "loginRestaurantPageFailed";
    }

    // /register create new Restaurant
    @RequestMapping(value = "/restaurant/register", method = RequestMethod.GET)
    public String newRestaurant(Model model) {
        model.addAttribute("givenAction", "/restaurant/register");
        model.addAttribute("givenUsername", "");
        model.addAttribute("givenPassword", "");
        model.addAttribute("givenrName","");
        return "registerRestaurantPage";
    }

    // /register save new Restaurant
    @RequestMapping(value = "/restaurant/register", method = RequestMethod.POST)
    public String addRestaurant(@RequestParam String uName, @RequestParam String psw, @RequestParam String rName) {
        Restaurant newRestaurant = new Restaurant(uName, psw, rName);
        restaurantRepository.save(newRestaurant);
        return "redirect:/restaurant/"+newRestaurant.getId().toString();
    }

    // /Restaurant/id# shows RestaurantPage
    @RequestMapping(value = "/restaurant/{id}", method = RequestMethod.GET)
    public String showRestaurant(@PathVariable(value = "id") Long restaurantId, Model model) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        model.addAttribute("restaurant",restaurant.get());
        return "restaurantPage";
    }

    // /Restaurant/id#/edit displays edit restaurant interface
    @RequestMapping(value = "/restaurant/{id}/edit", method = RequestMethod.GET)
    public String saveEditedRestaurant(@PathVariable(value = "id") Long restaurantId, Model model) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        model.addAttribute("givenAction", "/restaurant/"+restaurant.get().getId()+"/edit");
        model.addAttribute("givenUsername", restaurant.get().getUsername());
        model.addAttribute("givenPassword", restaurant.get().getPassword());
        model.addAttribute("givenrName", restaurant.get().getrName());
        return "editRestaurantPage";
    }

    // /edit saves edits to restaurant
    @RequestMapping(value = "/restaurant/{id}/edit", method = RequestMethod.POST)
    public String saveEditedRestaurant(@PathVariable(value = "id") Long restaurantId,
                                       @RequestParam String uName, @RequestParam String psw, @RequestParam String rName) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        restaurant.get().setUsername(uName);
        restaurant.get().setPassword(psw);
        restaurant.get().setrName(rName);
        restaurantRepository.save(restaurant.get());
        return "redirect:/restaurant/"+restaurant.get().getId().toString();
    }

    // /restaurant/delete/id# deletes restaurant by id
    @RequestMapping(value = "/restaurant/{id}/delete")
    public String deleteRestaurant(@PathVariable(value = "id") Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
        return "redirect:/restaurant";
    }

    // /Restaurant/id#/addMenu displays edit restaurant interface
    @RequestMapping(value = "/restaurant/{id}/editMenu", method = RequestMethod.GET)
    public String editRestaurantMenu(@PathVariable(value = "id") Long restaurantId, Model model) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        model.addAttribute("givenAction", "/restaurant/"+restaurant.get().getId()+"/editMenu");
        List<MenuItem> allMenuItemsForThisRestaurant = menuItemRepository.findAllByRestaurantID(restaurantId);
        model.addAttribute("givenrName", restaurant.get().getrName());
        model.addAttribute("menuItems", allMenuItemsForThisRestaurant);
        return "editRestaurantMenuPage";
    }

    // /EditMenuItem/menuItemID displays edit menuItem interface
    @RequestMapping(value = "/EditMenuItem/{id}", method = RequestMethod.GET)
    public String saveEditedMenuItem(@PathVariable(value = "id") Long menuItemId, Model model) {
        Optional<MenuItem> menuItem = menuItemRepository.findById(menuItemId);
        model.addAttribute("givenAction", "/EditMenuItem/"+menuItem.get().getId());
        model.addAttribute("givenAction2", "/EditMenuItem/"+menuItem.get().getId()+"/delete");
        model.addAttribute("givenItemName", menuItem.get().getName());
        model.addAttribute("givenDescription", menuItem.get().getDescription());
        model.addAttribute("givenPrice", menuItem.get().getPrice());
        return "editMenuItemPage";
    }

    // /edit saves edits to menuItem
    @RequestMapping(value = "/EditMenuItem/{id}", method = RequestMethod.POST)
    public String saveEditedMenuItem(@PathVariable(value = "id") Long menuItemId,
                                     @RequestParam String iName, @RequestParam String desc, @RequestParam double price) {
        Optional<MenuItem> menuItem = menuItemRepository.findById(menuItemId);
        menuItem.get().setName(iName);
        menuItem.get().setDescription(desc);
        menuItem.get().setPrice(price);
        menuItemRepository.save(menuItem.get());
        return "redirect:/restaurant/"+menuItem.get().getRestaurantID().toString()+"/editMenu";
    }

    // /restaurant/restaurantName/newMenuItem add new MenuItem
    @RequestMapping(value = "/restaurant/{id}/newMenuItem", method = RequestMethod.GET)
    public String newMenuItem(@PathVariable(value = "id") String restaurantName, Model model) {
        Optional<Restaurant> restaurant = restaurantRepository.findByrName(restaurantName);
        model.addAttribute("givenAction", "/restaurant/"+restaurantName+"/newMenuItem");
        model.addAttribute("givenrId",restaurant.get().getId());
        model.addAttribute("givenName", "");
        model.addAttribute("givenDescription", "");
        model.addAttribute("givenPrice","");
        return "addMenuItemPage";
    }

    // /restaurant/restaurantName/newMenuItem save new MenuItem
    @RequestMapping(value = "/restaurant/{id}/newMenuItem", method = RequestMethod.POST)
    public String newMenuItem(@PathVariable(value = "id") String restaurantName, @RequestParam String iName, @RequestParam String desc, @RequestParam double price) {
        Optional<Restaurant> restaurant = restaurantRepository.findByrName(restaurantName);
        MenuItem newMenuItem = new MenuItem(restaurant.get().getId(),iName, desc, price);
        menuItemRepository.save(newMenuItem);
        return "redirect:/restaurant/" + restaurant.get().getId().toString() + "/editMenu";
    }

    // /Edit/{id} deletes MenuItem by id
    @RequestMapping(value = "/EditMenuItem/{id}/delete", method = RequestMethod.POST)
    public String deleteMenuItem(@PathVariable(value = "id") Long menuItemId) {
        Optional<MenuItem> menuItem = menuItemRepository.findById(menuItemId);
        menuItemRepository.deleteById(menuItemId);
        return "redirect:/restaurant/" + menuItem.get().getRestaurantID().toString() + "/editMenu";
    }

    //Order home page
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String orderHome(Model model){
        List<Restaurant> allRestaurants = restaurantRepository.findAll();
        model.addAttribute("restaurants", allRestaurants);
        return "order";
    }

    // /order/restaurantName/showMenu displays restaurant menu
    @RequestMapping(value = "/order/{id}/showMenu", method = RequestMethod.GET)
    public String showRestaurantMenu(@PathVariable(value = "id") String restaurantId, Model model) {
        Optional<Restaurant> restaurant = restaurantRepository.findByrName(restaurantId);
        List<MenuItem> allMenuItemsForThisRestaurant = menuItemRepository.findAllByRestaurantID(restaurant.get().getId());
        model.addAttribute("menuItems", allMenuItemsForThisRestaurant);
        return "restaurantShowMenuPage";
    }

    // /addToCart/menuItemId add item to cart
    @RequestMapping(value = "/addToCart/{id}", method = RequestMethod.POST)
    public String addToCart(@PathVariable(value = "id") Long menuItemId) {
        cart.save(menuItemRepository.findById(menuItemId).get().menuToCart(cart));
        return "redirect:/displayCart";
    }

    // /removeFromCart/menuItemId remove item from cart
    @RequestMapping(value = "/removeFromCart/{id}", method = RequestMethod.POST)
    public String removeFromCart(@PathVariable(value = "id") Long cartItemId) {
        Optional<CartItem> cartItem = cart.findById(cartItemId);
        if(cartItem.get().getQuantity()>1){
            cartItem.get().setQuantity((cartItem.get().getQuantity()-1));
            cart.save(cartItem.get()); //if quantity greater than 1 just decrease quantity by 1, else delete item from cart
        }else{cart.deleteById(cartItemId);}
        return "redirect:/displayCart";
    }

    // /Checkout remove all items from cart
    @RequestMapping(value = "/Checkout", method = RequestMethod.GET)
    public String Checkout() {
        cart.deleteAll();
        return "redirect:/displayCart";
    }

    @RequestMapping(value ="/displayCart", method = RequestMethod.GET)
    public String displayCart(Model model){
        List<CartItem> cartItems = cart.findAll();
        Double total=0.0;
        for (CartItem cartItem : cartItems) { total += (cartItem.getPrice() * cartItem.getQuantity()); }
        model.addAttribute("totalPrice",total);
        model.addAttribute("cartItems",cartItems);
        return "displayCart";
    }
}