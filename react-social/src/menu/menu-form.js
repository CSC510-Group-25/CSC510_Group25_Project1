import * as React from 'react';
import Button from '@material-ui/core/Button';
import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker
} from '@material-ui/pickers';
import DialogTitle from '@material-ui/core/DialogTitle';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import TextField from "@material-ui/core/TextField";
import {API_BASE_URL} from "../constants";
import Alert from "react-s-alert";
import DateFnsUtils from '@date-io/date-fns';

class MenuForm extends React.Component {
  constructor(props) {
    super(props);

    this.state = { open: false, dishName: "", orderQuantity: 0, date: new Date() }

    this.handleChange = this.handleChange.bind(this);
    this.handleOpen = this.handleOpen.bind(this);
    this.handleClose = this.handleClose.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleBoughtDateChange = this.handleBoughtDateChange.bind(this);
    this.handleExpDateChange = this.handleExpDateChange.bind(this);
  }

  handleClose() {
    this.setState({open: false});
  };

  handleOpen() {
    this.setState({open: true});
  };

  handleChange(event) {
    const target = event.target;
    const inputName = target.name;
    const inputValue = target.value;

    this.setState({
      [inputName] : inputValue
    });
  };

  handleBoughtDateChange(date) {
    this.setState({dateBought : date});
  }

  handleSubmit(event) {
    event.preventDefault();

    fetch(API_BASE_URL + '/addOrder', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        orderID : "o1",
        dishID : "d1",
        restaurantID : "r1",
        orderQuantity : this.state.OrderQuantity,
        dishName : this.state.dishName,
        date : this.state.date
      })
    })
      .then(response => {
        this.setState({open: false});
      }).catch(error => {
      Alert.error((error && error.message) || 'Oops! Something went wrong. Please try again!');
    });
  }

  render() {

    return (
      <div>
        <Button variant="outlined" onClick={this.handleOpen}>
          Add New Menu Item
        </Button>
        <Dialog open={this.state.open} onClose={this.handleClose}
                aria-labelledby="form-dialog-title">
          <DialogTitle id="form-dialog-title">Add New Menu</DialogTitle>
          <DialogContent>
            <TextField
              autoFocus
              margin="dense"
              id="dishName"
              value={this.state.dishName}
              onChange={this.handleChange}
              name="dishName"
              label="Dish Name"
              fullWidth
            />
            <TextField
              margin="dense"
              id="batchQty"
              value={this.state.orderQuantity}
              onChange={this.handleChange}
              name="orderQuantity"
              label="Quantity"
              type="number"
              fullWidth
            />
            <br/>
            <MuiPickersUtilsProvider utils={DateFnsUtils}>
              <KeyboardDatePicker
                label="Date Orderered"
                value={this.state.date}
                onChange={this.handleBoughtDateChange}
                name={"dateordered"}
              />
            </MuiPickersUtilsProvider>
            <br/>
          </DialogContent>
          <DialogActions>
            <Button onClick={this.handleClose} color="primary">
              Cancel
            </Button>
            <Button onClick={this.handleSubmit} color="primary">
              Save
            </Button>
          </DialogActions>
        </Dialog>
      </div>
    );
  }
}

export default MenuForm;
