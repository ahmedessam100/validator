import { BrowserRouter, Route } from 'react-router-dom';
import DataLister from './components/DataLister';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Route exact path='/' component={DataLister}/>
      </div>
    </BrowserRouter>
  );
}

export default App;
