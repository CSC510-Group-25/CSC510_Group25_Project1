import React, { Component } from 'react';
import './Profile.css';

class Profile extends Component {
    constructor(props) {
        super(props);
        console.log(props);
        this.state = {
                    restaurantName: '',
                    name: '',
                    email: '',
                    password: '',
                    role: ''
                }
                this.handleInputChange = this.handleInputChange.bind(this);
                this.handleSubmit = this.handleSubmit.bind(this);

    }
     handleInputChange(event) {
            const target = event.target;
            const inputName = target.name;
            const inputValue = target.value;

            this.setState({
                [inputName] : inputValue
            });
        }

        handleSubmit(event) {
            event.preventDefault();

            const signUpRequest = Object.assign({}, this.state);

//            signup(signUpRequest)
//            .then(response => {
//                Alert.success("You're successfully registered. Please login to continue!");
//                this.props.history.push("/login");
//            }).catch(error => {
//                Alert.error((error && error.message) || 'Oops! Something went wrong. Please try again!');
//            });
        }
    render() {
        return (
            <div className="profile-container">
                <div className="container">
                    <div className="profile-info">
                        <div className="profile-avatar">
                            { 
                                this.props.currentUser.imageUrl ? (
                                    <img src={this.props.currentUser.imageUrl} alt={this.props.currentUser.name}/>
                                ) : (
                                    <div className="text-avatar">
                                        <span>{this.props.currentUser.name && this.props.currentUser.name[0]}</span>

                                    </div>
                                )
                            }
                        </div>
                        <div className="profile-name">
                          <h2>{this.props.currentUser.name}</h2>

                          <form onSubmit={this.handleSubmit}>
                                          <div className="form-item">
                                              <input type="text" name="restaurantName"
                                                  className="form-control" placeholder={this.props.currentUser.restaurantName}
                                                  value={this.state.restaurantName} onChange={this.handleInputChange} required/>
                                          </div>

                                          <div className="form-item">
                                              <input type="text" name="name"
                                                  className="form-control" placeholder={this.props.currentUser.name}
                                                  value={this.state.name} onChange={this.handleInputChange} required/>
                                          </div>


                                          <div className="form-item">
                                              <input type="text" name="role"
                                                  className="form-control" placeholder={this.props.currentUser.role}
                                                  value={this.state.role}  onChange={this.handleInputChange} required/>
                                          </div>
                                          <div className="form-item">
                                              <button type="submit" className="btn btn-block btn-primary" >Update</button>
                                          </div>
                                      </form>




                        </div>
                    </div>
                </div>    
            </div>
        );
    }
}




export default Profile