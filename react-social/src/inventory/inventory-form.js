import * as React from 'react';
import Button from '@material-ui/core/Button';
//import DatePickers from '@material-ui/pickers/DatePicker';
import DialogTitle from '@material-ui/core/DialogTitle';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import TextField from "@material-ui/core/TextField";
import {API_BASE_URL} from "../constants";
import Alert from "react-s-alert";

class InventoryForm extends React.Component {
  constructor(props) {
    super(props);

    this.state = { open: false, itemName: "", batchQty: 0, costPerItem: 0, dateBought: 0, dateExpired: 0 }

    this.handleChange = this.handleChange.bind(this);
    this.handleOpen = this.handleOpen.bind(this);
    this.handleClose = this.handleClose.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
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


  handleSubmit(event) {
    event.preventDefault();

    fetch(API_BASE_URL + '/addInventory', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        restaurantName : "demo",
        restaurantID : "r1",
        itemID : "i1",
        itemName : this.state.itemName,
        batchID : "b1",
        batchQty : this.state.batchQty,
        costPerItem : 200,
        dateBought : 20210101,
        dateExpired : 20220101
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
          Add New Inventory
        </Button>
        <Dialog open={this.state.open} onClose={this.handleClose}
                aria-labelledby="form-dialog-title">
          <DialogTitle id="form-dialog-title">Add New Inventory</DialogTitle>
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
              fullWidth
            />
            <TextField
              margin="dense"
              id="costPerItem"
              value={this.state.costPerItem}
              onChange={this.handleChange}
              name="costPerItem"
              label="Cost per Item"
              fullWidth
            />
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

export default InventoryForm;
