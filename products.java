//products.java
package com.example.sheoran.mykeep2;


class products {
    private int _id;
    private String _name;
    private int _amount;

    public products(){

    }

    public products(String _name) {
        this._name = _name;
    }

    public products(int _amount, String _name) {
        this._amount = _amount;
        this._name = _name;
    }

    public int get_amount() {
        return _amount;
    }

    public void set_amount(int _amount) {
        this._amount = _amount;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }
}
