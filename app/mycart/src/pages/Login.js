import React from "react";
import axios from "axios";

class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state={
            username:'',
            password:''
        }
        this.handleUsernameChange = this.handleUsernameChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }


    async componentDidMount() {
        const resp = await axios.get("http://localhost:9090/")
        const data = await resp.data;
        console.log(data)
    }

    render() {
        return (
            <div style={{display:"flex", justifyContent:"center", alignItems:"center"}}>
                <form>
                    <div>UserName
                        <input type="text" name="username" value={this.state.username} onChange={this.handleUsernameChange}></input>
                    </div>
                    <div>Password
                        <input type="password" name="password" value={this.state.password} onChange={this.handlePasswordChange}></input>
                    </div>
                    <div>
                        <button onClick={this.handleSubmit} >Login</button>
                    </div>
                </form>
            </div>
        )
    }
    handleSubmit(e){
        e.preventDefault();
        const data ={
            username: this.state.username,
            password: this.state.password
        }
        console.log(data)
        alert("form Submited");
    }

    handlePasswordChange(e) {
       this.setState({
           password: e.target.value
       })
        console.log(e.target.value)
    }

    handleUsernameChange(e) {
        this.setState({
            username: e.target.value
        })
        console.log(e.target.value)
    }
}

export default Login;