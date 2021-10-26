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

    this.state = { open: false, itemName: "", batchQty: 0, costPerItem: 0, dateBought: new Date(), dateExpired: new Date() }

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
  handleExpDateChange(date) {
    this.setState({dateExpired : date});
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
              id="itemName"
              value={this.state.itemName}
              onChange={this.handleChange}
              name="itemName"
              label="Item Name"
              fullWidth
            />
            <TextField
              margin="dense"
              id="batchQty"
              value={this.state.batchQty}
              onChange={this.handleChange}
              name="batchQty"
              label="Quantity"
              type="number"
              fullWidth
            />
            <TextField
              margin="dense"
              id="costPerItem"
              value={this.state.costPerItem}
              onChange={this.handleChange}
              name="costPerItem"
              label="Cost per Item"
              type="number"
              fullWidth
            />
            <br/>
            <MuiPickersUtilsProvider utils={DateFnsUtils}>
              <KeyboardDatePicker
                label="Date Bought"
                value={this.state.dateBought}
                onChange={this.handleBoughtDateChange}
                name={"dateBought"}
              />
            </MuiPickersUtilsProvider>
            <br/>
            <br/>
            <MuiPickersUtilsProvider utils={DateFnsUtils}>
              <KeyboardDatePicker
                label="Date Item(s) will expire"
                value={this.state.dateExpired}
                onChange={this.handleExpDateChange}
                name={"dateExpired"}
              />
            </MuiPickersUtilsProvider>
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
