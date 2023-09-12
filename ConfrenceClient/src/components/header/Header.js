import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTicket } from "@fortawesome/free-solid-svg-icons";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { NavLink } from "react-router-dom";

const Header = () => {
    return(
        <Navbar bg="dark blue" variant="dark" expand="lg">
            <Container fluid>
                <Navbar.Brand href="/" style={{"color": 'white '}}>
                    {/* <FontAwesomeIcon icon="fa-solid fa-child-reaching" />  */}
                    Kidzland-Confrences
                </Navbar.Brand>
                <Navbar.Toggle aria-controls = "navbarScroll" />
                <Navbar.Collapse id="navbarScroll">
                    <Nav 
                        className="me-auto my-2 my-lg-0" 
                        style={{maxHeight: '100px'}}
                        navbarScroll
                    >
                        <NavLink className="nav-link" to="/">Home</NavLink>
                        

                    </Nav>

                    

                </Navbar.Collapse>
                
            </Container>

        </Navbar>
    )
}

export default Header