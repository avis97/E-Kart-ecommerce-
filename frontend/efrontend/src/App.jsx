
import { Routes,Route } from "react-router-dom";
import CustomerRouter from "./Routers/CustomerRouter";



function App() {
  return (
    <>
    <Routes>
      <Route path="/*" element={<CustomerRouter/>}></Route>
    </Routes>
    </>
  );
}

export default App;
