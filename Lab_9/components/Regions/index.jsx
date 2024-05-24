import React from "react";
import "./styles.css";

/**
 * Define Regions, a React component of Lab9. The model
 * data for this view (the regions names) is available at
 * window.lab9models.regionsModel().
 */
class Regions extends React.Component {
  constructor(props) {
    super(props);
    // Initialize the state with an empty substring and the full list of regions
    this.state = {
      substr: "",
      filteredRegions: []
    };

    this.handleChangeSubstr = (event) => this.handleChange(event);
  }

  handleChange = (event) => {
    const substr = event.target.value.toLowerCase();
    const filteredRegions = window.lab9models
      .regionsModel()
      .filter(region => region.toLowerCase().includes(substr))
      .sort();

    this.setState({
      substr,
      filteredRegions
    });
  };

  render() {
    return (
      <div>
        <div className="state-search">
          {this.state.substr}
        </div>
        <div className="lab9-example-output">
          <span id='IInfo'>
            {this.state.filteredRegions.length > 0 ? (
              <p>
                {this.state.filteredRegions.map(region => (
                  <li key={region}>{region}</li>
                ))}
              </p>
            ) : (
              "No matching regions found"
            )}
          </span>
        </div>
        <label htmlFor="substrId">Enter substring to search:</label>
        <input
          id="substrId"
          type="text"
          value={this.state.substr}
          onChange={this.handleChangeSubstr}
        />
      </div>
    );
  }
}

export default Regions;
